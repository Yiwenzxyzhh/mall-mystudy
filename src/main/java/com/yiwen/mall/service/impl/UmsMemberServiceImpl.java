package com.yiwen.mall.service.impl;

import com.yiwen.mall.common.exception.Asserts;
import com.yiwen.mall.common.api.ResultCodeEnum;
import com.yiwen.mall.common.utils.JwtTokenUtil;
import com.yiwen.mall.dao.mapper.UmsMemberLevelMapper;
import com.yiwen.mall.dao.mapper.UmsMemberMapper;
import com.yiwen.mall.dao.model.UmsMember;
import com.yiwen.mall.dao.model.UmsMemberLevel;
import com.yiwen.mall.dto.MemberDetails;
import com.yiwen.mall.pubdef.bo.UmsMemberLevelQueryBO;
import com.yiwen.mall.pubdef.bo.UmsMemberQueryBO;
import com.yiwen.mall.service.RedisService;
import com.yiwen.mall.service.UmsMemberCacheService;
import com.yiwen.mall.service.UmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ywxie
 * @date 2020/10/13 13:54
 * @describe 会员管理Service实现类
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    @Autowired
    private UmsMemberCacheService umsMemberCacheService;

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            sb.append(random.nextInt(10));
        }
        umsMemberCacheService.setAuthCode(telephone, sb.toString());
        return sb.toString();
    }

    //对输入的验证码进行校验
    @Override
    public boolean verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)){
            Asserts.fail(ResultCodeEnum.VERIFY_CODE_NULL);
        }
        String realAuthCode = umsMemberCacheService.getAuthCode(telephone);
        //为防止出现空指针，用authCode（已经判过空）
        return authCode.equals(realAuthCode);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember umsMember = getUserByUsername(username);
        if (umsMember != null){
            return new MemberDetails(umsMember);
        }
        return null;
    }

    @Override
    public UmsMember register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail(ResultCodeEnum.VERIFY_CODE_INCORRECT);
        }
        //查询是否已有该用户
        List<UmsMember> umsMembers = listUserByQueryBO(new UmsMemberQueryBO(username, telephone));
        if (!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail(ResultCodeEnum.USER_ALREADY_EXIST);
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //获取默认会员等级并设置
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.listByQueryBO(new UmsMemberLevelQueryBO(1));
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }
        umsMemberMapper.insert(umsMember);
        umsMember.setPassword(null);
        return umsMember;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
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

    /**
     * 刷新token
     */
    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    public List<UmsMember> listUserByQueryBO(UmsMemberQueryBO queryBO){
        return umsMemberMapper.listByQueryBO(queryBO);
    }

    private UmsMember getUserByUsername(String username){
        List<UmsMember> umsMembers = listUserByQueryBO(new UmsMemberQueryBO(username, null));
        if (!CollectionUtils.isEmpty(umsMembers)){
            return umsMembers.get(0);
        }
        return null;
    }
}
