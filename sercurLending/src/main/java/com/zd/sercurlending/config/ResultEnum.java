package com.zd.sercurlending.config;

public enum ResultEnum {

    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(200,"成功"),
    DATA_IS_NULL(200,"数据为空"),
    USER_NOT_EXIST(1,"用户不存在"),
    USER_IS_EXISTS(2,"用户已存在")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
