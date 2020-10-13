package com.yiwen.mall.service;

import com.yiwen.mall.common.api.CommonResult;

/**
 * @author ywxie
 * @date 2020/10/13 13:51
 * @describe 会员管理Service
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
