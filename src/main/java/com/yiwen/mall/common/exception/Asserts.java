package com.yiwen.mall.common.exception;

import com.yiwen.mall.common.api.IErrorCode;

/**
 * @author ywxie
 * @date 2020/11/10 15:19
 * @describe 断言处理类，用于抛出各种API异常
 */
public class Asserts {
    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
