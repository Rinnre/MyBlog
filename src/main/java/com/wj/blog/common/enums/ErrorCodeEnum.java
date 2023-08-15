package com.wj.blog.common.enums;

import org.springframework.http.HttpStatus;

/**
 * 错误代码枚举
 *
 * @author w
 * @date 2023/06/12
 */
public enum ErrorCodeEnum {

    RESOURCE_NOT_FOUND(201, HttpStatus.NOT_FOUND, "未找到该资源"),
    REQUEST_VALIDATION_FAILED(202, HttpStatus.BAD_REQUEST, "请求数据格式验证失败");
    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCodeEnum(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
