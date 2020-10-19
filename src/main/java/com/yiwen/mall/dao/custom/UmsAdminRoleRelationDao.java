package com.yiwen.mall.dao.custom;

import com.yiwen.mall.dao.model.UmsPermission;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/14 15:33
 * @describe 后台用户与角色管理自定义Dao
 */
@Repository
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

}
