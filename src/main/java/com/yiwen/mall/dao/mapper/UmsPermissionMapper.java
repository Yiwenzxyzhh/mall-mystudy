package com.yiwen.mall.dao.mapper;

import com.yiwen.mall.dao.model.UmsPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsPermission record);

    int insertSelective(UmsPermission record);

    UmsPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsPermission record);

    int updateByPrimaryKey(UmsPermission record);

    List<UmsPermission> selectAll();
}
