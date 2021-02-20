package com.yiwen.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiwen.mall.common.exception.Asserts;
import com.yiwen.mall.common.api.ResultCodeEnum;
import com.yiwen.mall.common.utils.JwtTokenUtil;
import com.yiwen.mall.common.utils.RequestUtil;
import com.yiwen.mall.dao.custom.UmsAdminRoleRelationDao;
import com.yiwen.mall.dao.mapper.UmsAdminLoginLogMapper;
import com.yiwen.mall.dao.mapper.UmsAdminMapper;
import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.dao.model.UmsAdminLoginLog;
import com.yiwen.mall.dao.model.UmsPermission;
import com.yiwen.mall.dao.model.UmsRole;
import com.yiwen.mall.dto.AdminUserDetails;
import com.yiwen.mall.pubdef.bo.UmsAdminQueryBO;
import com.yiwen.mall.pubdef.pubenum.UmsAdminStatusEnum;
import com.yiwen.mall.service.UmsAdminCacheService;
import com.yiwen.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/14 10:47
 * @describe
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    //专门用来操作Redis缓存的业务类
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdminByUsername(username);
        if (admin != null){
            return admin;
        }
        List<UmsAdmin> adminList = adminMapper.listByQueryBO(new UmsAdminQueryBO(null, username, false));
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(UmsAdminStatusEnum.EFFECTIVE.getStatus());
        //查询是否有相同用户名的用户
        List<UmsAdmin> umsAdminList = adminMapper.listByQueryBO(new UmsAdminQueryBO(null, umsAdmin.getUsername(), false));
        if (umsAdminList.size() > 0) {
            //错误提示：已存在相同用户名
            Asserts.fail(ResultCodeEnum.USER_NAME_DUPLICATED);
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (userDetails == null){
                Asserts.fail(ResultCodeEnum.USERNAME_OR_PASSWORD_INCORRECT);
            }
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateTokenByUserDetails(userDetails);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin == null){
            Asserts.fail(ResultCodeEnum.USER_NOT_EXIST);
        }
        UmsAdminLoginLog adminLoginLog = new UmsAdminLoginLog();
        adminLoginLog.setAdminId(admin.getId());
        adminLoginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        adminLoginLog.setIp(RequestUtil.getRequestIp(request));
        int count = loginLogMapper.insertSelective(adminLoginLog);
        if (count > 0){
            UmsAdmin updateAdmin = new UmsAdmin();
            updateAdmin.setId(admin.getId());
            updateAdmin.setLoginTime(adminLoginLog.getCreateTime());
            adminMapper.updateByPrimaryKeySelective(updateAdmin);
        }
    }

    /**
     * 刷新token
     */
    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    /**
     * 获取用户对于角色
     */
    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    /**
     * 获取用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            //获取用户的权限
            List<UmsPermission> permissionList = getPermissionList(admin.getId());
            return new AdminUserDetails(admin, permissionList);
        }
        throw new UsernameNotFoundException(ResultCodeEnum.USERNAME_OR_PASSWORD_INCORRECT.getMessage());
    }

    /**
     * 根据用户名或姓名分页获取用户列表
     */
    @Override
    public List<UmsAdmin> listAdmin(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isEmpty(username)){
            return listAll();
        }
        //模糊查询匹配
        return adminMapper.listByQueryBO(new UmsAdminQueryBO(null, "%" + username + "%", true));
    }

    @Override
    public List<UmsAdmin> listAll() {
        return adminMapper.listByQueryBO(new UmsAdminQueryBO(null, null, null));
    }

    /**
     * 根据用户id获取用户
     *
     * @param id
     */
    @Override
    public UmsAdmin getAdminById(Long id) {
        UmsAdmin admin = adminMapper.selectByPrimaryKey(id);
        if (admin == null){
            Asserts.fail(ResultCodeEnum.USER_NOT_EXIST);
        }
        return admin;
    }

    /**
     * 修改指定用户信息
     */
    @Override
    public int updateAdmin(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
        if (rawAdmin.getPassword().equals(admin.getPassword())){
            admin.setPassword(null);
        }else {
            if (StringUtils.isEmpty(admin.getPassword())){
                Asserts.fail(ResultCodeEnum.PASSWORD_NOT_ALLOWED_NULL);
            }
            //将密码进行加密操作
            String encodePassword = passwordEncoder.encode(admin.getPassword());
            admin.setPassword(encodePassword);
        }
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        adminCacheService.delAdmin(admin.getId());
        return count;
    }
}
