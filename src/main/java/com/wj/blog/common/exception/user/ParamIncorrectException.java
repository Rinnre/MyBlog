package com.wj.blog.common.exception.user;

import com.wj.blog.common.enums.ErrorCodeEnum;
import com.wj.blog.common.exception.BaseException;


/**
 * 参数不正确异常
 *
 * @author wj
 * {@code @date} 2023/03/02
 */
public class ParamIncorrectException extends BaseException {

    public ParamIncorrectException(ErrorCodeEnum error, String message) {
        super(error, message);
    }

    public ParamIncorrectException(String message) {
        super(ErrorCodeEnum.W205, message);
    }
}
