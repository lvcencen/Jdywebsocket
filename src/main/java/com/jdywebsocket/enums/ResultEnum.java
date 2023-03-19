package com.jdywebsocket.enums;


/**
 * @author: lvChen
 * @date: 2022/9/13 11:14
 * @description: 返回常量定义
 */
public enum ResultEnum implements IResultEnum {
    /**
     * 状态
     */
    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未获授权"),
    FORBIDDEN(403, "无此权限"),
    NOT_FOUND(404, "页面不存在"),
    NO_LOGIN(405, "未登陆"),
    METHOD_NOT_ALLOWED(406, "未登陆"),
    SERVER_ERROR(500, "服务器内部错误"),
    BAD_GATEWAY(502, "网关错误"),
    GATEWAY_TIMEOUT(504, "网络请求超时"),
    LIMIT_EXCEEDED(509, "服务器繁忙，请稍后重试"),
    FAST_FAIL(589, "操作失败"),
    DO_HTTP_FAILED(599, "请求HTTP API失败/未获得数据"),
    NO_DATA(600, "数据不存在"),
    USER_EXISTS(601, "用户已存在"),
    LOGIN_PASSWORD_ERROR(602, "登录账号或密码错误，请检查后输入"),
    USER_LOCKED(604, "用户已锁定"),
    USER_UNABLE(605, "用户已注销"),
    DATA_FORMAT_ERROR(606, "数据格式错误"),
    OPENID_ERROR(607, "OpenId错误"),
    DATA_EXISTS(608, "数据已存在，请勿重复添加"),
    USER_TOKEN_EXPIRE(610, "用户登录Token已过期"),
    CANCEL_REQUEST(660, "取消请求"),
    PASSWORD_ERROR(661, "原密码错误，请重新输入"),
    UNKNOWN_ERROR(700, "未知错误"),
    /**
     * 短信验证码相关
     */
    SMS_ERROR(603, "验证码错误，请重新输入"),
    SMS_CODE_EXPIRE(1010, "验证码已过期，请重新获取"),
    SMS_CODE_OVER_LIMIT(1011, "验证码发送次数已超过限制，请明天再试"),
    SMS_CODE_FREQUENTLY(1012, "短信发送过于频繁，请1分钟后再试。"),
    SMS_PHONE_LOCKED(1013, "该手机号已被冻结，请联系客服"),
    SMS_SEND_FAIL(100119, "验证码发送失败，请稍后再试");


    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
