package com.yiwen.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiwen.mall.common.api.ResultCodeEnum;
import com.yiwen.mall.common.exception.Asserts;
import com.yiwen.mall.dao.custom.UmsRoleDao;
import com.yiwen.mall.dao.mapper.UmsAdminRoleRelationMapper;
import com.yiwen.mall.dao.mapper.UmsRoleMapper;
import com.yiwen.mall.dao.model.UmsMenu;
import com.yiwen.mall.dao.model.UmsRole;
import com.yiwen.mall.pubdef.bo.UmsRoleQueryBO;
import com.yiwen.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    /**
     * 根据管理员ID获取对应菜单
     */
    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    /**
     * 获取所有角色列表
     */
    @Override
    public List<UmsRole> listAll() {
        return roleMapper.listByQueryBO(new UmsRoleQueryBO(null, null, false));
    }

    /**
     * 分页获取角色列表
     */
    @Override
    public List<UmsRole> listByPage(String roleName, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isEmpty(roleName)){
            return listAll();
        }
        return roleMapper.listByQueryBO(new UmsRoleQueryBO("%" + roleName + "%", null, true));
    }

    /**
     * 根据roleId更新角色用户数
     */
    @Override
    public void updateRoleAdminCount(List<Long> roleIds) {
        for (Long roleId: roleIds){
            //先看有没有这个角色
            UmsRole role = roleMapper.selectByPrimaryKey(roleId);
            if (role == null){
                Asserts.fail(ResultCodeEnum.ROLE_NOT_EXIST);
            }
            //先根据roleIds从关系表中取出数量
            int adminCount = adminRoleRelationMapper.countAdminByRoleId(roleId);
            role.setAdminCount(adminCount);
            //再根据roleIds更新
            int count = roleMapper.updateByPrimaryKeySelective(role);
            if (count < 0){
                Asserts.fail(ResultCodeEnum.FAILED);
            }
        }
    }
}
