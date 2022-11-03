package com.wj.blog.util;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 * @author wj
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    PARAM_ERROR( 202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    DATA_UPDATE_ERROR(205, "数据版本异常"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    CODE_ERROR(210, "验证码错误"),
//    LOGIN_MOBLE_ERROR(211, "账号不正确"),
    LOGIN_DISABLED_ERROR(212, "改用户已被禁用"),
    REGISTER_MOBILE_ERROR(213, "手机号已被使用"),
    LOGIN_ACL(215, "没有权限"),

    URL_ENCODE_ERROR( 216, "URL编码失败"),
    ILLEGAL_CALLBACK_REQUEST_ERROR( 217, "非法回调请求"),
    FETCH_ACCESS_TOKEN_FAILED( 218, "获取accessToken失败"),
    FETCH_USERINFO_ERROR( 219, "获取用户信息失败"),
    //LOGIN_ERROR( 23005, "登录失败"),

    PAY_RUN(220, "支付中"),
    CANCEL_ORDER_FAIL(225, "取消订单失败"),
    CANCEL_ORDER_NO(225, "不能取消预约"),
    UPDATE_DATA_ERROR(205,"更新数据失败"),
    LOGIN_ACCT_IN_USED(230, "登录账号已经存在"),
    SAVE_DATA_ERROR(231,"保存数据失败"),
    ROLE_NAME_ALREADY_EXIST(232, "该角色已经存在"),
    DELETE_DATA_ERROR(233, "删除数据失败"),
    DATA_DELETE_ERROR_HAS_CHILDREN(234,"菜单存在子节点、删除失败" ),
    SEND_CODE_ERROR(235, "短信验证码发送失败"),
    LOGIN_DATA_ERROR(236,"手机或者验证码为空" ),
    NETWORK_ERROR(237,"服务器网络异常，请稍后重试" ),
    LOGIN_PHONE_ERROR(238, "手机或者密码为空"),
    LOGIN_PHONE_OR_PASSWORD_ERROR(239, "手机或者密码不正确");
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
