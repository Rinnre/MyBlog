package com.wj.blog.common.thread;

import com.wj.blog.common.enums.RedisOperationEnum;
import com.wj.blog.common.enums.SysnchroDataOperationEnum;
import com.wj.blog.common.util.RedisUtil;
import com.wj.blog.model.entity.Statistics;
import com.wj.blog.service.StatisticsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private StatisticsService statisticsService;

    private static final String REDIS_HEAD = "statistics";

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

    /**
     * 同步数据
     * redis->mysql
     *
     * @param operation 操作
     * @return {@link TimerTask}
     */
    public TimerTask synchroDataRedis(Integer operation) {
        return new TimerTask() {
            @Override
            public void run() {
                List<Statistics> statisticsList = statisticsService.list();
                if (SysnchroDataOperationEnum.REDIS_TO_MYSQL.getValue().equals(operation)) {
                    List<String> keys = new ArrayList<>();
                    statisticsList.forEach(statistics -> keys.add(REDIS_HEAD + statistics.getSourceId()));
                    List<Object> objects = redisUtil.mGetObjectByKey(keys);
                    List<Statistics> statisticList = new ArrayList<>();
                    objects.forEach(object->{
                        Statistics statistics =(Statistics) object;
                        statisticList.add(statistics);
                    });
                    statisticsService.saveOrUpdateBatch(statisticList);
                }
                if (SysnchroDataOperationEnum.MYSQL_TO_REDIS.getValue().equals(operation)) {
                    HashMap<String, Object> map = new HashMap<>(12);
                    statisticsList.forEach(statistics -> map.put(REDIS_HEAD + statistics.getSourceId(), statistics));
                    redisUtil.mSetKeyObject(map);
                }
            }
        };
    }


}
