package com.funkey.status;

public enum ResultCodeEnum {
    SUCCESS(200, "Success"),
    FAIL(201, "Fail"),
    SERVICE_ERROR(500, "Service error"),
    ILLEGAL_REQUEST(400, "Illegal request"),
    NO_LOGIN(401, "Not logged in"),
    NO_AUTH(403, "No permission");

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
