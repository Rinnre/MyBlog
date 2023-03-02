package com.wj.blog.common.enums;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 * @author wj
 */
@Getter
public enum ResultCodeEnum {

    /**
     * 成功状态码
     */
    SUCCESS(200, "成功"),
    /**
     * 失败状态码
     */
    FAIL(201, "失败"),
    /**
     * 参数不正确状态码
     */
    PARAM_ERROR(202, "参数不正确");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
