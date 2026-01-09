package com.funkey.utils;

import com.funkey.status.StatusCode;

public class ResultUtils {
    //success
    public static ResultVo success() {
        return Vo(null, StatusCode.SUCCESS_CODE, null);
    }
    public static ResultVo success(String msg) {
        return Vo(msg, StatusCode.SUCCESS_CODE, null);
    }
    public static ResultVo success(String msg, Object data) {
        return Vo(msg, StatusCode.SUCCESS_CODE, data);
    }
    public static ResultVo success(String msg, int code, Object data) {
        return Vo(msg, code, data);
    }

    //factory function
    public static ResultVo Vo(String msg, int code, Object data) {
        return new ResultVo(msg, code, data);
    }

    //error
    public static ResultVo error() {
        return Vo(null, StatusCode.ERROR_CODE, null);
    }
    public static ResultVo error(String msg) {
        return Vo(msg, StatusCode.ERROR_CODE, null);
    }
    public static ResultVo error(String msg, int code) {
        return Vo(msg, code, null);
    }
    public static ResultVo error(String msg, Object data) {
        return Vo(msg, StatusCode.ERROR_CODE, data);
    }
    public static ResultVo error(String msg, int code, Object data) {
        return Vo(msg, code, data);
    }
}
