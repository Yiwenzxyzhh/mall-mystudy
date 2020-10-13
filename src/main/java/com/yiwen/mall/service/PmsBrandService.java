package com.yiwen.mall.service;

import com.yiwen.mall.dao.model.PmsBrand;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/6/19 19:20
 * @describe 实现PmsBrand表中的添加、修改、删除及分页查询接口。
 */
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int addBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrandById(Long id);
}
