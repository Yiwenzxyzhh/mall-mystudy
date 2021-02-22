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
    @Value("${redis.database}")
    private String REDIS_DATABASE;
//    @Value("${redis.expire.common}")
//    private Long REDIS_EXPIRE;
//    @Value("${redis.key.admin}")
//    private String REDIS_KEY_ADMIN;
//    @Value("${redis.key.resourceList}")
//    private String REDIS_KEY_RESOURCE_LIST;
    private final String redisDataBaseStr = REDIS_DATABASE + ":";

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return null;
    }

    /**
     * 删除后台用户缓存
     *
     * @param adminId
     */
    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            String key = genRedisAdminKey(adminId);
//            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    private String genRedisAdminKey(Long adminId){
        return String.format(redisDataBaseStr + RedisConstant.REDIS_KEY_ADMIN, adminId);
    }

    /**
     * 删除后台用户资源列表缓存
     *
     * @param adminId
     */
    @Override
    public void delResourceList(Long adminId) {
//        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        String key = genRedisResourceListKey(adminId);
        redisService.del(key);
    }

    private String genRedisResourceListKey(Long adminId){
        return String.format(redisDataBaseStr + RedisConstant.REDIS_KEY_RESOURCE_LIST, adminId);
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
