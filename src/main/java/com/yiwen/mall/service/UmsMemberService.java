package com.yiwen.mall.service;

import com.yiwen.mall.common.api.CommonResult;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ywxie
 * @date 2020/10/13 13:51
 * @describe 会员管理Service
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    boolean verifyAuthCode(String telephone, String authCode);

    /**
     * 刷新token
     */
    String refreshToken(String token);
    UserDetails loadUserByUsername(String username);

    /**
     * 登录
     */
    String login(String username, String password);
}
