package com.wj.blog.common.exception.user;

import com.wj.blog.common.enums.ErrorCodeEnum;
import com.wj.blog.common.exception.BaseException;

import java.util.Map;

/**
 * 参数不正确异常
 *
 * @author wj
 * @date 2023/03/02
 */
public class ParamIncorrectException extends BaseException {

    public ParamIncorrectException(Map<String, Object> data) {
        super(ErrorCodeEnum.REQUEST_VALIDATION_FAILED, data);
    }
}
