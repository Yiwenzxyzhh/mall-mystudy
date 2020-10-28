package com.yiwen.mall.controller;

import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.pubdef.dto.UmsPermissionNode;
import com.yiwen.mall.service.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/10/28 15:26
 * @describe 后台用户权限管理
 */
@RestController
@Api(tags = "UmsPermissionController", description = "后台用户权限管理")
public class UmsPermissionController {

    private UmsPermissionService permissionService;

    @ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public CommonResult<List<UmsPermissionNode>> treeList() {
        List<UmsPermissionNode> permissionNodeList = permissionService.treeList();
        return CommonResult.success(permissionNodeList);
    }

    @ApiOperation("Stream API测试")
    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    public CommonResult streamTest() {
        permissionService.streamTest();
        return CommonResult.success(null,"操作成功");
    }
}
