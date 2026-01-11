package com.funkey.status;

public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(500, "服务异常"),
    ILLEGAL_REQUEST(400, "非法请求"),
    NO_LOGIN(401, "未登录"),
    NO_AUTH(403, "没有权限");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
