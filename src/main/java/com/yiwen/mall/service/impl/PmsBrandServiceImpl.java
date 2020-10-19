package com.yiwen.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiwen.mall.dao.model.PmsBrand;
import com.yiwen.mall.dao.mapper.PmsBrandMapper;
import com.yiwen.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/6/19 19:27
 * @describe
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
//        return null;
        return brandMapper.listAll();
    }

    @Override
    public int addBrand(PmsBrand brand) {
        return brandMapper.insert(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //之后进行查询操作将自动进行分页
        List<PmsBrand> brandList = listAllBrand();
        return brandList;
    }

    @Override
    public PmsBrand getBrandById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
