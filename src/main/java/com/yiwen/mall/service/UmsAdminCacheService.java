package com.yiwen.mall.service;

import com.yiwen.mall.dao.model.UmsAdmin;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/11/16 15:37
 * @describe 后台用户缓存操作类
 */
public interface UmsAdminCacheService {

    UmsAdmin getAdminByUsername(String username);

    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);
}
