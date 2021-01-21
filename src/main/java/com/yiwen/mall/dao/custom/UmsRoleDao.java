package com.yiwen.mall.dao.custom;

import com.yiwen.mall.dao.model.UmsMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ywxie
 * @date 2021/1/20 16:21
 * @describe 自定义后台角色管理Dao
 */
@Component
public interface UmsRoleDao {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
}
