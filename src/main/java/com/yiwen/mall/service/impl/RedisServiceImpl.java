package com.yiwen.mall.service.impl;

import com.yiwen.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author ywxie
 * @date 2020/10/13 13:45
 * @describe redis操作Service的实现类
 */
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存属性
     */
    @Override
    public void set(String key, Object value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 存储数据
     */
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取数据
     *
     * @param key
     */
    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置超期时间
     *
     * @param key
     * @param expire
     */
    @Override
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 删除数据
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 自增操作
     *
     * @param key
     * @param delta 自增步长
     */
    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 删除属性
     */
    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }
}
