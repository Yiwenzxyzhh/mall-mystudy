package com.yiwen.mall.controller;

import com.google.common.collect.ImmutableMap;
import com.yiwen.mall.common.exception.Asserts;
import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.common.api.ResultCodeEnum;
import com.yiwen.mall.dao.model.UmsMember;
import com.yiwen.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ywxie
 * @date 2020/10/13 14:10
 * @describe 会员登录注册管理Controller
 */
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("获取验证码")
    @GetMapping("/auth_code")
    public CommonResult getAuthCode(@RequestParam String telephone){
        return CommonResult.success(ImmutableMap.of("auth_code", umsMemberService.generateAuthCode(telephone)), ResultCodeEnum.GET_VERIFY_CODE_SUCCESS);
    }


    @ApiOperation("判断验证码是否正确")
    @PostMapping("/auth_code/verify")
    public CommonResult verifyAuthCode(@RequestParam String telephone, @RequestParam String authCode){
        boolean verifyResult = umsMemberService.verifyAuthCode(telephone, authCode);
        if (!verifyResult){
            Asserts.fail(ResultCodeEnum.VERIFY_CODE_FAILED);
        }
        return CommonResult.success(ResultCodeEnum.VERIFY_CODE_SUCCESS);
    }

    @ApiOperation("会员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        UmsMember umsMember = umsMemberService.register(username, password, telephone, authCode);
        return CommonResult.success(umsMember, ResultCodeEnum.REGISTER_SUCCESS);
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password) {
        String token = umsMemberService.login(username, password);
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
        String refreshToken = umsMemberService.refreshToken(token);
        if (refreshToken == null) {
            Asserts.fail(ResultCodeEnum.UNAUTHORIZED);
        }
        return CommonResult.success(ImmutableMap.of("token", refreshToken, "tokenHead", tokenHead));
    }

}
