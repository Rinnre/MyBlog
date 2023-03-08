package com.wj.blog.common.exception.cdn;

/**
 * 文件上传异常
 *
 * @author wj
 * @date 2023/03/02
 */
public class FileUploadException extends RuntimeException {
    public FileUploadException(String message) {
        super(message);
    }
}
