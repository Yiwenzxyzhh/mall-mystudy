package com.yiwen.mall.service;

import com.yiwen.mall.pubdef.dto.UmsPermissionNode;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/28 15:28
 * @describe 后台用户权限管理Service
 */
public interface UmsPermissionService {

    /**
     * 以层级结构返回所有权限
     */
    List<UmsPermissionNode> treeList();
    void streamTest();
}
