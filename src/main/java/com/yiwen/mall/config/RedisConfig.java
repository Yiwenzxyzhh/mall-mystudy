package com.yiwen.mall.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author ywxie
 * @date 2021/2/20 15:34
 * @describe Redis配置类
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {
}
