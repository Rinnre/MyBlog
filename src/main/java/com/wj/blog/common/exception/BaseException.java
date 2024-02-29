package com.wj.blog.common.exception;


import com.wj.blog.common.enums.ErrorCodeEnum;


/**
 * 基础异常抽象类
 *
 * @author w
 * {@code @date} 2023/06/12
 */
public abstract class BaseException extends RuntimeException {

    private final ErrorCodeEnum error;
    private final String msg;

    public BaseException(ErrorCodeEnum error, String message) {
        super(error.getMessage());
        this.error = error;
        this.msg = message;
    }

    protected BaseException(ErrorCodeEnum error, String message, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        this.msg = message;
    }

    public ErrorCodeEnum getError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }
}
