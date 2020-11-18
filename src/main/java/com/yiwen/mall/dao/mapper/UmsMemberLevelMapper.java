package com.yiwen.mall.dao.mapper;

import com.yiwen.mall.dao.model.UmsMemberLevel;
import com.yiwen.mall.pubdef.bo.UmsMemberLevelQueryBO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsMemberLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLevel record);

    int insertSelective(UmsMemberLevel record);

    UmsMemberLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberLevel record);

    int updateByPrimaryKey(UmsMemberLevel record);

    List<UmsMemberLevel> listByQueryBO(UmsMemberLevelQueryBO queryBO);
}
