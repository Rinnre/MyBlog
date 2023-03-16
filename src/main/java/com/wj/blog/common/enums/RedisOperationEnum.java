package com.wj.blog.common.enums;

/**
 * redis操作
 *
 * @author wj
 * @date 2023/03/16
 */
public enum RedisOperationEnum {
    /**
     *
     */
    DELETE(0),
    INSERT_UPDATE(1);
    private Integer value;

    RedisOperationEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
