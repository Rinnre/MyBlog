package com.wj.blog.common.enums;

/**
 * 分类枚举类型
 *
 * @author wj
 * @date 2023/02/28
 */
public enum CategoryTypeEnum {
    /**
     * 分类枚举类型
     */
    CATEGORY("分类", 0),
    TAG("标签", 1);
    private final String type;
    private final Integer value;

    CategoryTypeEnum(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }
}
