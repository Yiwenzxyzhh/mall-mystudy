package com.yiwen.mall.common.api;

/**
 * @author ywxie
 * @date 2020/10/12 10:21
 * @describe
 */
public enum ResultCodeEnum implements IErrorCode {
    //common
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    //ums
    USER_NAME_DUPLICATED(1000, "该昵称已被占用"),
    VERIFY_CODE_NULL(1001,"请输入验证码"),
    VERIFY_CODE_FAILED(1002,"验证码校验失败"),
    GET_VERIFY_CODE_SUCCESS(1003,"获取验证码成功"),
    VERIFY_CODE_SUCCESS(1003,"验证码校验成功"),
    USERNAME_OR_PASSWORD_INCORRECT(1004,"用户名或密码错误"),
    REGISTER_SUCCESS(1005, "注册成功"),
    VERIFY_CODE_INCORRECT(1006,"验证码错误"),
    USER_ALREADY_EXIST(1007,"用户已经存在"),
    PASSWORD_NOT_ALLOWED_NULL(1008,"密码不能为空"),
    USER_NOT_EXIST(1009,"用户不存在"),

    //order
    ORDER_SUCCESS(2000,"下单成功"),

    //admin


    ;

    private long code;
    private String message;

    ResultCodeEnum(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
