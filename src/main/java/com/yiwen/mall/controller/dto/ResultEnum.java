package com.yiwen.mall.controller.dto;

/**
 * @author ywxie
 * @date 2020/10/12 11:32
 * @describe
 */
public enum ResultEnum {
    //common
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    //ums
    UMS_USER_NAME_DUPLICATED(1000, "该昵称已被占用"),
    ;



    private int state;
    private String msg;

    ResultEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public static ResultEnum stateOf(int index) {
        for (ResultEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
