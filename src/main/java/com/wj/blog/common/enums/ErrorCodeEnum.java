package com.wj.blog.common.enums;


/**
 * 错误代码枚举
 *
 * @author w
 * {@code @date} 2023/06/12
 */
public enum ErrorCodeEnum {

    /**
     * 操作成功
     **/
    W200(200, "操作成功"),
    /**
     * 操作失败
     **/
    W201(201, "操作失败"),
    /**
     * 服务限流
     **/
    W202(202, "服务开启限流保护,请稍后再试!"),
    /**
     * 服务降级
     **/
    W203(203, "服务开启降级保护,请稍后再试!"),
    /**
     * 热点参数限流
     **/
    W204(204, "热点参数限流,请稍后再试!"),
    /**
     * 系统规则不满足
     **/
    W205(205, "系统规则不满足要求,请稍后再试!"),
    /**
     * 授权规则不通过
     **/
    W206(206, "授权规则不通过,请稍后再试!"),
    W400(400, "存在空指针异常"),
    /**
     * access_denied
     **/
    W403(403, "无访问权限,请联系管理员授予权限"),
    W404(404, "请求的资源不存在"),
    /**
     * access_denied
     **/
    W401(401, "匿名用户访问无权限资源"),
    W405(405, "请求方式不支持!"),

    /**
     * 服务异常
     **/
    W500(500, "系统异常，请稍后重试"),

    INVALID_TOKEN(2001, "访问令牌不合法"),
    ACCESS_DENIED(2003, "没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED(1001, "客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式");

    private final int code;


    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;

        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
