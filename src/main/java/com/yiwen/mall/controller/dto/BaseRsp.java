package com.yiwen.mall.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author ywxie
 * @date 2020/10/12 11:31
 * @describe
 */
@JsonInclude
public class BaseRsp implements Serializable {

    private int code;
    private String message;
    private Object data;


}
