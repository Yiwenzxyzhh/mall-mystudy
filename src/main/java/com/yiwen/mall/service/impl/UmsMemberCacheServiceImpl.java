package com.yiwen.mall.service.impl;

import com.yiwen.mall.component.annotation.CacheException;
import com.yiwen.mall.service.RedisService;
import com.yiwen.mall.service.UmsMemberCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author ywxie
 * @date 2020/11/16 15:51
 * @describe UmsMemberCacheService实现类
 */
@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;


    @CacheException
    @Override
    public void setAuthCode(String telephone, String authCode) {
        //验证码绑定手机号并存储到redis
        String key = REDIS_KEY_PREFIX_AUTH_CODE + telephone;
        redisService.set(key, authCode);
        redisService.expire(key, AUTH_CODE_EXPIRE_SECONDS);
    }

    @CacheException
    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_KEY_PREFIX_AUTH_CODE + telephone;
        return (String) redisService.get(key);
    }

}
