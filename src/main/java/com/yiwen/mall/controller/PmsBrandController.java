package com.yiwen.mall.controller;

import com.google.common.collect.ImmutableMap;
import com.yiwen.mall.common.api.CommonPage;
import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.dao.model.PmsBrand;
import com.yiwen.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/6/19 19:00
 * @describe 品牌管理Controller
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("获取所有品牌列表")
    @GetMapping(value = "listAll")
    public CommonResult getBrandList() {
        return CommonResult.success(ImmutableMap.of("brand_list", brandService.listAllBrand()));
    }

    @PreAuthorize("hasAuthority('pms:brand:create')")
    @ApiOperation("添加品牌")
    @PostMapping(value = "")
    public CommonResult addBrand(@RequestBody PmsBrand pmsBrand){
        brandService.addBrand(pmsBrand);
        return CommonResult.success();
    }

    @PreAuthorize("hasAuthority('pms:brand:update')")
    @ApiOperation("更新指定id品牌信息")
    @PutMapping("/{id}")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDTO, BindingResult bindingResult){
        brandService.updateBrand(id, pmsBrandDTO);
        return CommonResult.success();
    }

    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @ApiOperation("删除指定id的品牌")
    @DeleteMapping("/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id){
        brandService.deleteBrand(id);
        return CommonResult.success();
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(ImmutableMap.of("brand_list",CommonPage.restPage(brandList)));
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("获取指定id品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult brand(@PathVariable("id") Long id) {
        return CommonResult.success(ImmutableMap.of("brand_detail",brandService.getBrandById(id)));
    }
}
