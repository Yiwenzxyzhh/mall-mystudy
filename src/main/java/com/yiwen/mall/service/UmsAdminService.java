package com.yiwen.mall.service;

import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.dao.model.UmsPermission;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/14 10:46
 * @describe 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String userName);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
