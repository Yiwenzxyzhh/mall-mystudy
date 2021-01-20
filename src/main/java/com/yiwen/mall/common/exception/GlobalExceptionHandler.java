package com.yiwen.mall.common.exception;

import com.yiwen.mall.common.api.CommonResult;
import com.yiwen.mall.common.exception.ApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ywxie
 * @date 2020/11/10 15:20
 * @describe 全局异常处理,并返回封装好的CommonResult对象
 * @ControllerAdvice 类似于@Component注解，可以指定一个组件，
 *                    这个组件主要用于增强@Controller注解修饰的类的功能，比如说进行全局异常处理。
 * @ExceptionHandler 用来修饰全局异常处理的方法，可以指定异常的类型。
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }
}
