package com.yiwen.mall.dao.mapper;

import com.yiwen.mall.dao.model.UmsRole;
import com.yiwen.mall.pubdef.bo.UmsRoleQueryBO;

import java.util.List;

public interface UmsRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertSelective(UmsRole record);

    UmsRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsRole record);

    int updateByPrimaryKey(UmsRole record);

    List<UmsRole> listByQueryBO(UmsRoleQueryBO queryBO);
}
