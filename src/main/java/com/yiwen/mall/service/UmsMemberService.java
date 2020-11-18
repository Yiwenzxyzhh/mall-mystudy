package com.yiwen.mall.service;

import com.yiwen.mall.dao.model.UmsMember;
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
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 注册
     */
    UmsMember register(String username, String password, String telephone, String authCode);

    /**
     * 登录后获取token
     */
    String login(String username, String password);

    /**
     * 刷新token
     */
    String refreshToken(String token);
}
