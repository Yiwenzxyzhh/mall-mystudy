package com.yiwen.mall.nosql.elasticsearch.repository;

import com.yiwen.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ywxie
 * @date 2020/11/30 16:10
 * @describe 商品ES操作类
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
    /**
     * 搜索查询
     *在接口中直接指定查询方法名称便可查询，无需进行实现，如商品表中有商品名称、标题和关键字，直接定义以下查询，就可以对这三个字段进行全文搜索。
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);

    /**
     * 使用@Query注解可以用Elasticsearch的DSL语句进行查询
     */
//    @Query("{"bool" : {"must" : {"field" : {"name" : "?0"}}}}")
//    Page<EsProduct> findByName(String name, Pageable pageable);

}
