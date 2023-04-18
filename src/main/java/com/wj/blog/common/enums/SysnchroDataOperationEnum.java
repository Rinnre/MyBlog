package com.wj.blog.common.enums;

/**
 * 数据同步操作类型枚举
 *
 * @author wj
 * @date 2023/4/18 16:16
 */
public enum SysnchroDataOperationEnum {
    /**
     * redis->mysql 0
     * mysql->redis 1
     */
    REDIS_TO_MYSQL(0),
    MYSQL_TO_REDIS(1);

    private Integer value;

    SysnchroDataOperationEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
