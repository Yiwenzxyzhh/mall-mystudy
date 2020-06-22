package com.yiwen.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiwen.mall.mbg.mapper.PmsBrandMapper;
import com.yiwen.mall.mbg.model.PmsBrand;
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
        return brandMapper.listAll();
    }

    @Override
    public int createBrand(PmsBrand brand) {
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
        //通过构造PageInfo对象获取分页信息，如当前页码，总页数，总条数
        PageInfo<PmsBrand> pageInfo = new PageInfo<PmsBrand>(brandList);
        return brandList;
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
