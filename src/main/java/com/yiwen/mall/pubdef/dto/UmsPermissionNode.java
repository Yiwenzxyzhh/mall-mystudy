package com.yiwen.mall.pubdef.dto;

import com.yiwen.mall.dao.model.UmsPermission;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/28 15:30
 * @describe 定义包含下级权限的对象
 * 继承自UmsPermission对象，之增加了一个children属性，用于存储下级权限。
 */
public class UmsPermissionNode extends UmsPermission {
    private List<UmsPermissionNode> children;

    public List<UmsPermissionNode> getChildren() {
        return children;
    }

    public void setChildren(List<UmsPermissionNode> children) {
        this.children = children;
    }
}
