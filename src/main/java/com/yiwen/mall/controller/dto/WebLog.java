package com.yiwen.mall.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ywxie
 * @date 2020/10/27 15:28
 * @describe Controller层的日志封装类
 * 用于封装需要记录的日志信息，包括操作的描述、时间、消耗时间、url、请求参数和返回结果等信息。
 */
@Getter
@Setter
public class WebLog {
    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 请求返回的结果
     */
    private Object result;
}
