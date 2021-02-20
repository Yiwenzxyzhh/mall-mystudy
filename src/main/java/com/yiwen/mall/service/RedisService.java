package com.yiwen.mall.service;

/**
 * @author ywxie
 * @date 2020/10/13 13:44
 * @describe redis操作service，对象和数组都以json的形式进行存储
 */
public interface RedisService {

    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    Object get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

    /**
     * 删除属性
     */
    Boolean del(String key);
}
