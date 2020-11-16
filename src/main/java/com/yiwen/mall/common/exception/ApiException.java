package com.yiwen.mall.common.exception;

import com.yiwen.mall.common.api.IErrorCode;

/**
 * @author ywxie
 * @date 2020/11/10 14:55
 * @describe 自定义API异常
 * 使用全局异常处理来处理校验逻辑的思路很简单，首先我们需要通过@ControllerAdvice注解定义一个全局异常的处理类，
 * 然后自定义一个校验异常，当我们在Controller中校验失败时，直接抛出该异常，这样就可以达到校验失败返回错误信息的目的了。
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
