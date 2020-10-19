package com.yiwen.mall.dao.mapper;

import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.pubdef.bo.UmsAdminQueryBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UmsAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin record);

    int insertSelective(UmsAdmin record);

    UmsAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdmin record);

    int updateByPrimaryKey(UmsAdmin record);

    List<UmsAdmin> listByQueryBO(UmsAdminQueryBO queryBO);
}
