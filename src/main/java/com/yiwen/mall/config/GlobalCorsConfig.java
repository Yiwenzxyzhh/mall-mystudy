package com.yiwen.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ywxie
 * @date 2020/10/27 16:38
 * @describe 全局跨域配置
 * 添加GlobalCorsConfig配置文件来允许跨域访问。
 * 什么是跨域问题:
 * CORS全称Cross-Origin Resource Sharing，意为跨域资源共享。
 * 当一个资源去访问另一个不同域名或者同域名不同端口的资源时，就会发出跨域请求。
 * 如果此时另一个资源不允许其进行跨域资源访问，那么访问的那个资源就会遇到跨域问题。
 * 跨域资源共享（CORS）是前后端分离项目很常见的问题
 * 使用mall项目的源代码来演示一下跨域问题。此时前端代码运行在8090端口上，后端代码运行在8080端口上，
 * 由于其域名都是localhost，但是前端和后端运行端口不一致，此时前端访问后端接口时，就会产生跨域问题。
 */
@Configuration
public class GlobalCorsConfig {
    /**
     * 允许跨域调用的过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //允许所有域名进行跨域调用
        config.addAllowedOrigin("*");
        //允许跨越发送cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
