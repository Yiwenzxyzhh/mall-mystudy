package com.yiwen.mall.dao.custom;

import com.yiwen.mall.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ywxie
 * @date 2021/1/11 16:40
 * @describe 搜索系统中的商品管理自定义Dao
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
