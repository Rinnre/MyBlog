package com.wj.blog.common.enums;

/**
 * 记录类型枚举
 *
 * @author wj
 * @date 2023/03/08
 */
public enum RecordTypeEnum {
    /**
     * 记录类型枚举
     */
    LIKE("点赞", "Like", 0),
    COLLECT("收藏", "Collect", 1);

    private final String label;
    private final String name;
    private final Integer value;

    RecordTypeEnum(String label, String name, Integer value) {
        this.label = label;
        this.name = name;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
