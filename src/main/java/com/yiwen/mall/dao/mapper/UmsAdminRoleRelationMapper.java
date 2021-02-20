package com.yiwen.mall.dao.mapper;

import com.yiwen.mall.dao.model.UmsAdminRoleRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsAdminRoleRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation record);

    int insertSelective(UmsAdminRoleRelation record);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminRoleRelation record);

    int updateByPrimaryKey(UmsAdminRoleRelation record);

    int deleteByAdminId(Long adminId);
}
