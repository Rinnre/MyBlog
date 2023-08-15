package com.wj.blog.common.exception.user;

import com.wj.blog.common.enums.ErrorCodeEnum;
import com.wj.blog.common.exception.BaseException;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author wj
 * @date 2023/3/24 17:36
 */
public class ResourceNotExistException extends BaseException {

    public ResourceNotExistException(Map<String, Object> data) {
        super(ErrorCodeEnum.RESOURCE_NOT_FOUND, data);
    }
}
