package com.wj.blog.common.exception.user;

/**
 * Created by IntelliJ IDEA
 *
 * @author wj
 * @date 2023/3/24 17:36
 */
public class ResourceNotExistException extends RuntimeException{
    public ResourceNotExistException(String message) {
        super(message);
    }
}
