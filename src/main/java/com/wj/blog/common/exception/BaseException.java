package com.wj.blog.common.exception;


import com.wj.blog.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础异常抽象类
 *
 * @author w
 * @date 2023/06/12
 */
public abstract class BaseException extends RuntimeException {

    private final ErrorCodeEnum error;
    private final HashMap<String, Object> data = new HashMap<>();

    public BaseException(ErrorCodeEnum error, Map<String, Object> data) {
        super(error.getMessage());
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    protected BaseException(ErrorCodeEnum error, Map<String, Object> data, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public ErrorCodeEnum getError() {
        return error;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
