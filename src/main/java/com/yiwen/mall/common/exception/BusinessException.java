package com.yiwen.mall.common.exception;

import com.yiwen.mall.common.api.ResultCodeEnum;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ResultCodeEnum resultEnum;

    private Object data;

    public BusinessException(ResultCodeEnum resultEnum) {
        this(resultEnum, null);
    }

    public BusinessException(ResultCodeEnum resultEnum, Object data) {
        super(resultEnum.getMessage());
        this.resultEnum = resultEnum;
        this.data = data;
    }

    public long getErrorCode() {
        return resultEnum.getCode();
    }

    public ResultCodeEnum getResultEnum() {
        return resultEnum;
    }

    public Object getData() {
        return data;
    }

}
