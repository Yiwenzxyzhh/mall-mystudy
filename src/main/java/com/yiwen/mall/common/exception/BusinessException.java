package com.yiwen.mall.common.exception;

import com.yiwen.mall.controller.dto.ResultEnum;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ResultEnum resultEnum;

    private Object data;

    public BusinessException(ResultEnum resultEnum) {
        this(resultEnum, null);
    }

    public BusinessException(ResultEnum resultEnum, Object data) {
        super(resultEnum.getMsg());
        this.resultEnum = resultEnum;
        this.data = data;
    }

    public int getErrorCode() {
        return resultEnum.getState();
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public Object getData() {
        return data;
    }

}
