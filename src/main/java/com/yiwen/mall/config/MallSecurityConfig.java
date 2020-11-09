package com.yiwen.mall.config;

import com.yiwen.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author ywxie
 * @date 2020/11/9 13:16
 * @describe mall-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        //获取登录用户信息
        return username -> umsMemberService.loadUserByUsername(username);
    }
}
