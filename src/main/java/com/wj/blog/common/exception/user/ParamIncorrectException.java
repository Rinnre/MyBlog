package com.wj.blog.common.exception.user;

/**
 * 参数不正确异常
 *
 * @author wj
 * @date 2023/03/02
 */
public class ParamIncorrectException extends RuntimeException {
    public ParamIncorrectException(String message) {
        super(message);
    }
}
