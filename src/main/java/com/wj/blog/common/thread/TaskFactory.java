package com.wj.blog.common.thread;

import com.wj.blog.common.enums.RedisOperationEnum;
import com.wj.blog.common.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.TimerTask;

/**
 * 任务工厂
 *
 * @author wj
 * @date 2023/03/16
 */
@Component
public class TaskFactory {

    @Resource
    private RedisUtil redisUtil;

    public TimerTask redisOperation(String id, Object value, Integer operation) {
        return new TimerTask() {
            @Override
            public void run() {
                if (RedisOperationEnum.DELETE.getValue().equals(operation)) {
                    redisUtil.removeKeyValue(id);
                }
                if (RedisOperationEnum.DELETE.getValue().equals(operation)) {
                    redisUtil.setKeyObject(id, value);
                }
            }
        };
    }
}
