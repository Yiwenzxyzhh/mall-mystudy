package com.yiwen.mall.dao.mapper;

import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.pubdef.bo.UmsAdminQueryBO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin record);

    int insertSelective(UmsAdmin record);

    UmsAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdmin record);

    int updateByPrimaryKey(UmsAdmin record);

    List<UmsAdmin> listByQueryBO(UmsAdminQueryBO queryBO);
}
