package com.wj.blog.common.enums;

/**
 * 统计类型枚举
 *
 * @author wj
 * @date 2023/03/08
 */
public enum StatisticsTypeEnum {
    /**
     * 统计类型枚举
     */
    ADD_VIEW("addView"),
    ADD_COMMENT("addComment"),
    ADD_LIKE("addLike"),
    ADD_COLLECT("addCollect"),
    SUB_COMMENT("subComment"),
    SUB_LIKE("subLike"),
    SUB_COLLECT("subCollect"),
    ARTICLE_LIST("articleList"),
    DYNAMIC_LIST("dynamicList");
    private final String type;

    StatisticsTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
