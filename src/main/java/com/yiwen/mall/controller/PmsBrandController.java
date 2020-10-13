package com.yiwen.mall.controller;

import com.yiwen.mall.common.api.CommonPage;
import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.dao.model.PmsBrand;
import com.yiwen.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation("获取所有品牌列表")
    @GetMapping(value = "listAll")
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @PostMapping(value = "")
    @ResponseBody
    public CommonResult addBrand(@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = brandService.addBrand(pmsBrand);
        if (count == 1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("addBrand success:{}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("addBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @PutMapping("/{id}")
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDTO, BindingResult bindingResult){
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrandDTO);
        if (count == 1){
            commonResult = CommonResult.success(pmsBrandDTO);
            LOGGER.debug("updateBrand success:{}", pmsBrandDTO);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDTO);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的品牌")
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id){
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定id品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrandById(id));
    }

    private CommonResult getCommonResult(int count, Object object, String logStr){
        CommonResult commonResult;
        if (count == 1){
            commonResult = CommonResult.success(object);
            LOGGER.debug(logStr + " success:{}", object);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug(logStr + " failed:{}", object);
        }
        return commonResult;
    }
}
