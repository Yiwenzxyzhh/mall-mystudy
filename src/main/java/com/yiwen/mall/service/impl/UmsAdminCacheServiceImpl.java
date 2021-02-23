package com.yiwen.mall.service.impl;

import com.yiwen.mall.component.cache.constant.RedisConstant;
import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.service.RedisService;
import com.yiwen.mall.service.UmsAdminCacheService;
import com.yiwen.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/11/16 15:38
 * @describe 后台用户缓存操作Service实现类
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsAdminService adminService;
//    @Value("${redis.database}")
//    private String REDIS_DATABASE;
    @Value("${redis.key.expire.common}")
    private Long REDIS_EXPIRE;

    /**
     * 获取缓存后台用户信息
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return (UmsAdmin) redisService.get(genRedisAdminKey(username));
    }

    /**
     * 设置缓存后台用户信息
     */
    @Override
    public void setAdmin(UmsAdmin admin) {
        redisService.set(genRedisAdminKey(admin.getUsername()), admin, REDIS_EXPIRE);
    }

    /**
     * 删除后台用户缓存
     */
    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            redisService.del(genRedisAdminKey(admin.getUsername()));
        }
    }

    private String genRedisAdminKey(String username){
        return String.format(RedisConstant.REDIS_KEY_ADMIN, username);
    }

    /**
     * 删除后台用户资源列表缓存
     *
     * @param adminId
     */
    @Override
    public void delResourceList(Long adminId) {
        redisService.del(genRedisResourceListKey(adminId));
    }

    private String genRedisResourceListKey(Long adminId){
        return String.format(RedisConstant.REDIS_KEY_RESOURCE_LIST, adminId);
    }

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     *
     * @param roleId
     */
    @Override
    public void delResourceListByRole(Long roleId) {

    }

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     *
     * @param roleIds
     */
    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     *
     * @param resourceId
     */
    @Override
    public void delResourceListByResource(Long resourceId) {

    }
}
