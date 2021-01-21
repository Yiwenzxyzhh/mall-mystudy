package com.yiwen.mall.controller.admin;

import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.dao.model.UmsRole;
import com.yiwen.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ywxie
 * @date 2020/11/18 14:58
 * @describe 后台用户角色管理
 */
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RestController
@RequestMapping("/role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult listAll() {
        List<UmsRole> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

}
