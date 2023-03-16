package com.wj.blog.common.enums;

/**
 * 统计数据枚举
 *
 * @author wj
 * @date 2023/02/27
 */
public enum StatisticsEnum {
    /**
     * 统计表所属枚举
     */
    ARTICLE("文章", 0),
    DYNAMIC("动态", 1);
    private final String name;
    private final Integer value;

    StatisticsEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
