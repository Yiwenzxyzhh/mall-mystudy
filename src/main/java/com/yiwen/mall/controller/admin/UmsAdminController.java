package com.yiwen.mall.controller.admin;

import com.google.common.collect.ImmutableMap;
import com.yiwen.mall.common.api.CommonPage;
import com.yiwen.mall.common.exception.Asserts;
import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.common.api.ResultCodeEnum;
import com.yiwen.mall.dao.model.UmsAdmin;
import com.yiwen.mall.dao.model.UmsPermission;
import com.yiwen.mall.dao.model.UmsRole;
import com.yiwen.mall.dto.UmsAdminLoginParam;
import com.yiwen.mall.service.UmsAdminService;
import com.yiwen.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ywxie
 * @date 2020/10/14 10:42
 * @describe 后台用户管理
 */
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            Asserts.fail(ResultCodeEnum.USERNAME_OR_PASSWORD_INCORRECT);
        }
        return CommonResult.success(ImmutableMap.of("token", token, "tokenHead", tokenHead));
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            Asserts.fail(ResultCodeEnum.UNAUTHORIZED);
        }
        return CommonResult.success(ImmutableMap.of("token", refreshToken, "tokenHead", tokenHead));
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public CommonResult getUserInfo(Principal principal){//Principal principal 走spring security验证的
        if(principal == null){
            Asserts.fail(ResultCodeEnum.FORBIDDEN);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("icon", umsAdmin.getIcon());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        if(!roleList.isEmpty()){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    public CommonResult listUmsAdmin(@RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<UmsAdmin> adminList = adminService.listAdmin(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping(value = "/permission/{adminId}")
    public CommonResult getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
