package com.yiwen.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiwen.mall.common.exception.Asserts;
import com.yiwen.mall.common.api.ResultCodeEnum;
import com.yiwen.mall.dao.model.PmsBrand;
import com.yiwen.mall.dao.mapper.PmsBrandMapper;
import com.yiwen.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandServiceImpl.class);

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.listAll();
    }

    @Override
    public void addBrand(PmsBrand brand) {
        int count = brandMapper.insert(brand);
        if (count == 1){
            LOGGER.debug("addBrand success:{}", brand);
        }else {
            LOGGER.debug("addBrand failed:{}", brand);
            Asserts.fail(ResultCodeEnum.FAILED);
        }
    }

    @Override
    public void updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        int count = brandMapper.updateByPrimaryKey(brand);
        if (count == 1){
            LOGGER.debug("updateBrand success:{}", brand);
        }else {
            LOGGER.debug("updateBrand failed:{}", brand);
            Asserts.fail(ResultCodeEnum.FAILED);
        }
    }

    @Override
    public void deleteBrand(Long id) {
        int count = brandMapper.deleteByPrimaryKey(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            Asserts.fail(ResultCodeEnum.FAILED);
        }
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
