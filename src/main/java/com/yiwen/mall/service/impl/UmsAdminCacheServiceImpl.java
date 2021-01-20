package com.yiwen.mall.service.impl;

import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.service.UmsAdminCacheService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/11/16 15:38
 * @describe
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {



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

    }

    /**
     * 删除后台用户资源列表缓存
     *
     * @param adminId
     */
    @Override
    public void delResourceList(Long adminId) {

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
