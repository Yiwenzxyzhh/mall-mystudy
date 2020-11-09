package com.yiwen.mall.dao.mapper;

import com.yiwen.mall.dao.model.UmsMember;
import com.yiwen.mall.pubdef.bo.UmsMemberQueryBO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertSelective(UmsMember record);

    UmsMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMember record);

    int updateByPrimaryKey(UmsMember record);

    List<UmsMember> listAll();

    UmsMember selectByQueryBO(UmsMemberQueryBO queryBO);
}
