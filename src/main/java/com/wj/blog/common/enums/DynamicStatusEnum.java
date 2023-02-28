package com.wj.blog.common.enums;

/**
 * 动态状态枚举
 *
 * @author wj
 * @date 2023/02/27
 */
public enum DynamicStatusEnum {
    /**
     * 动态状态枚举
     */
    NORMAL("正常", 1);
    private final String status;
    private final Integer value;


    DynamicStatusEnum(String status, Integer value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public Integer getValue() {
        return value;
    }
}
