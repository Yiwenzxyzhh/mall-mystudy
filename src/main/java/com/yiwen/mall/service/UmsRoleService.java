package com.yiwen.mall.service;

import com.yiwen.mall.dao.model.UmsMenu;

import java.util.List;

/**
 * @author ywxie
 * @date 2021/1/20 16:23
 * @describe 后台角色管理Service
 */
public interface UmsRoleService {

    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenu> getMenuList(Long adminId);
}
