package com.yiwen.mall.service.impl;

import com.yiwen.mall.dao.custom.UmsRoleDao;
import com.yiwen.mall.dao.model.UmsMenu;
import com.yiwen.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywxie
 * @date 2021/1/20 16:24
 * @describe 后台角色管理Service实现类
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleDao roleDao;

    /**
     * 根据管理员ID获取对应菜单
     */
    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }
}
