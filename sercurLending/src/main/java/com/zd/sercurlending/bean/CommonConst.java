package com.zd.sercurlending.bean;

/**
 * @description: TODO
 * @author Meko
 */
public enum CommonConst implements EnumDescribable {

    FAIL(400, "操作失败"),
    NOT_FOUND(404,"接口不存在"),
    INTERNAL_SERVER_ERROR(500,"内部错误，请联系管理员"),
    PW_ERROR_TOO_MANY_TIMES(400,"密码错误次数太多，一小时之内限制登陆"),
    PARAM_ERROR(400, "请求参数异常"),
    ERROR_HANDLE(121038, "非法操作"),
    SUCCESS(0, "操作成功"),
    INSERT_SUCCESS(1, "增加数据成功"),
    DELETE_SUCCESS(2, "删除数据成功"),
    UPDATE_SUCCESS(3, "更新数据成功"),
    IS_LOGIN(125099, "用户已登录"),
    LOGIN_SUCCESS(200, "登陆成功"),
    USER_NOT_EXISTS(400, "该用户不存在"),
    USER_LOGIN_FAULT(400, "用户名或密码错误"),
    USER_PHONE_FAULT(400, "该手机号未注册"),
    USER_PWD_FAULT(400, "请勿使用近期使用过的密码"),
    USER_MSGCODE_FAULT(400, "验证码错误"),
    PHONE_ALREADY_EXITS(126006, "手机号已被注册"),
    NO_POWER(126007, "该用户权限不足"),
    USER_NOT_LOGIN(126008, "用户未登录"),
    NO_DATA(400, "未请求到数据"),
    NO_DATA_SUCCESS(200, "记录不存在"),
    TO_USER_NOT_EXISTS(126010, "收件人不存在"),

    RECORD_IS_EXISTS(126011,"记录已存在"),
    LOGIN_OUT_SUCCESS(126012,"注销成功"),
    CHECK_SUCCESS(200,"验证成功"),
    CHECK_FAULT(400,"验证失败"),


    LOGIN_HAS_EXPIRED(400,"登陆已过期"),
    PARAMS_VALID_ERR(400, "参数验证错误"),
    UPLOAD_FAULT(126018, "文件上传失败"),
    DEL_DATA_FAULT(126019, "记录删除失败"),
    UP_DATA_FAULT(126020, "记录修改失败"),
    MOVE_FAULT(126021, "文件移动失败"),
    SMS_SEND_ERR(126022, "短信发送失败"),
    DATA_SYNC_ERR(126023, "用户同步失败"),

    WAYBILL_DEFAULT_ERR(126024, "不能删除默认快递单模板"),
    REGIONAL_HAVE_CHILD(126025, "不能删除有下级区域的记录"),


    REPLY_EMPTY_ERR(126030, "回复内容不能为空"),
    PRODUCT_REPLY_NOT_EXIST_ERR(126031, "该评价不存在"),
    REPLY_REPLY_EXIST_ERR(126032, "回复已存在不能再次回复"),;


    public Integer code;

    public String message;

    CommonConst(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据状态码获取状态码描述
     *
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code) {
        String result = null;
        for (CommonConst c : CommonConst.values()) {
            if (c.code.equals(code)) {
                result = c.message;
            }
        }
        return result;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
