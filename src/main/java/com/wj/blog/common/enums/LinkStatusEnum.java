package com.wj.blog.common.enums;

/**
 * 链接状态枚举
 *
 * @author wj
 * @date 2023/02/24
 */
public enum LinkStatusEnum {
    /**
     * 审核中，正常，修改中，已失效
     */
    UNDER_REVIEW("审核中", 0),
    NORMAL("正常", 1),
    UNDER_MODIFICATION("修改中", 2),
    EXPIRED("已失效", 3);
    private final String name;
    private final Integer value;

    LinkStatusEnum(String name, Integer value) {
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
