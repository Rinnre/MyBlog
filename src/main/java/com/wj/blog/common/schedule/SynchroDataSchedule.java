package com.wj.blog.common.schedule;

import com.wj.blog.common.enums.SysnchroDataOperationEnum;
import com.wj.blog.common.thread.AsyncManager;
import com.wj.blog.common.thread.TaskFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * 同步数据计划任务
 * Created by IntelliJ IDEA
 *
 * @author wj
 * @date 2023/4/18 16:57
 */
@Configuration
@EnableScheduling
public class SynchroDataSchedule {

    @Resource
    private TaskFactory taskFactory;

    @Scheduled(cron = "0 0 0 * * *")
    public void synchroRedisDataSchedule(){
        AsyncManager.me().execute(taskFactory.synchroDataRedis(SysnchroDataOperationEnum.REDIS_TO_MYSQL.getValue()));
    }

    @Scheduled(cron = "0 30 0 * * *")
    public void synchroMysqlDataSchedule(){
        AsyncManager.me().execute(taskFactory.synchroDataRedis(SysnchroDataOperationEnum.MYSQL_TO_REDIS.getValue()));
    }
}
