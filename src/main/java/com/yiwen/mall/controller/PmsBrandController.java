package com.yiwen.mall.controller;

import com.yiwen.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ywxie
 * @date 2020/6/19 19:00
 * @describe 品牌管理Controller
 */
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);


}
