package com.wj.blog.common.exception.user;

import com.wj.blog.common.enums.ErrorCodeEnum;
import com.wj.blog.common.exception.BaseException;

/**
 * Created by IntelliJ IDEA
 *
 * @author wj
 * {@code @date} 2023/3/24 17:36
 */
public class ResourceNotExistException extends BaseException {

    public ResourceNotExistException(String data) {
        super(ErrorCodeEnum.W500, data);
    }
}
