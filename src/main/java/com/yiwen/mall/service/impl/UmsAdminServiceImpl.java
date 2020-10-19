package com.yiwen.mall.service.impl;

import com.yiwen.mall.common.exception.BusinessException;
import com.yiwen.mall.common.utils.JwtTokenUtil;
import com.yiwen.mall.controller.dto.ResultEnum;
import com.yiwen.mall.dao.custom.UmsAdminRoleRelationDao;
import com.yiwen.mall.dao.mapper.UmsAdminMapper;
import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.dao.model.UmsPermission;
import com.yiwen.mall.pubdef.bo.UmsAdminQueryBO;
import com.yiwen.mall.pubdef.pubenum.UmsAdminStatusEnum;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private UserDetailsService userDetailsService;
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

    @Override
    public UmsAdmin getAdminByUsername(String userName) {
        UmsAdminQueryBO umsAdminQueryBO = new UmsAdminQueryBO();
        umsAdminQueryBO.setUserName(userName);
        List<UmsAdmin> adminList = adminMapper.listByQueryBO(umsAdminQueryBO);
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
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
        UmsAdminQueryBO umsAdminQueryBO = new UmsAdminQueryBO();
        umsAdminQueryBO.setUserName(umsAdmin.getUserName());
        List<UmsAdmin> umsAdminList = adminMapper.listByQueryBO(umsAdminQueryBO);
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
//        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            //错误提示：已存在相同用户名
            throw new BusinessException(ResultEnum.UMS_USER_NAME_DUPLICATED);
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
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateTokenByUserDetails(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

}
