package com.yiwen.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ywxie
 * @date 2020/6/18 22:36
 * @describe MyBatis配置类
 */
@Configuration
@MapperScan("com.yiwen.mall.dao")
public class MyBatisConfig {
}
