package com.wj.blog.common.enums;

/**
 * 图片来源枚举
 *
 * @author wj
 * @date 2023/02/27
 */
public enum ImageEnum {
    /**
     * 图片来源枚举
     */
    ARTICLE("文章", 0),
    DYNAMIC("动态", 1);
    private final String name;
    private final Integer value;

    ImageEnum(String name, Integer value) {
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
