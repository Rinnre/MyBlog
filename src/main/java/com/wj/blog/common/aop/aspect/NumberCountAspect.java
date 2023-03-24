package com.wj.blog.common.aop.aspect;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wj.blog.common.aop.annation.NumberCount;
import com.wj.blog.common.enums.RecordTypeEnum;
import com.wj.blog.common.enums.RedisOperationEnum;
import com.wj.blog.common.enums.StatisticsEnum;
import com.wj.blog.common.enums.StatisticsTypeEnum;
import com.wj.blog.common.thread.AsyncManager;
import com.wj.blog.common.thread.TaskFactory;
import com.wj.blog.common.util.RedisUtil;
import com.wj.blog.mapper.CommentMapper;
import com.wj.blog.mapper.RecordMapper;
import com.wj.blog.mapper.StatisticsMapper;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.dto.CommentDto;
import com.wj.blog.pojo.dto.DynamicDto;
import com.wj.blog.pojo.dto.RecordDto;
import com.wj.blog.pojo.entity.Comment;
import com.wj.blog.pojo.entity.Record;
import com.wj.blog.pojo.entity.Statistics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数量切面
 *
 * @author wj
 * @date 2023/03/08
 */
@Aspect
@Component
@Slf4j
public class NumberCountAspect {

    private static final String REDIS_HEAD = "statistics";

    private static final String DELIMITER = "_";

    @Resource
    private StatisticsMapper statisticsMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private RecordMapper recordMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private TaskFactory taskFactory;

    @Pointcut("@annotation(com.wj.blog.common.aop.annation.NumberCount)")
    public void numberCountPointcut() {
    }

    /**
     * 处理数量计数
     *
     *
     * @param proceedingJoinPoint 切入点
     * @return {@link Object}
     */
    @Around(value = "numberCountPointcut()")
    public Object handleNumberCount(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取被拦截的方法
        Method method = methodSignature.getMethod();
        // 获取方法上注解内容
        NumberCount annotation = method.getAnnotation(NumberCount.class);
        String mode = annotation.mode();
        Object proceed;
        try {
            proceed = proceedingJoinPoint.proceed();
            String key = null;
            Statistics statistics = null;
            // 从redis中获取文章、动态的统计数据
            if (proceed instanceof ArticleDto) {
                ArticleDto article = (ArticleDto) proceed;
                String id = article.getId();
                key = REDIS_HEAD + id;
                statistics = handleCount(article, key, mode);
                proceed = article;
            } else if (proceed instanceof DynamicDto) {
                DynamicDto dynamic = (DynamicDto) proceed;
                String id = dynamic.getId();
                key = REDIS_HEAD + id;
                statistics = handleCount(dynamic, key, mode);
                proceed = dynamic;
            } else if (StatisticsTypeEnum.ADD_COMMENT.getType().split(DELIMITER)[0].equals(mode)) {
                Object[] args = proceedingJoinPoint.getArgs();
                RecordDto record = (RecordDto) args[0];
                String sourceId = record.getSourceId();
                key = REDIS_HEAD + sourceId;
                statistics = handleCount(record, key, mode, sourceId);
            } else if (StatisticsTypeEnum.SUB_COMMENT.getType().split(DELIMITER)[0].equals(mode)) {
                Object[] args = proceedingJoinPoint.getArgs();
                String id = (String) args[0];
                Record record = recordMapper.selectById(id);
                RecordDto recordDto = new RecordDto();
                BeanUtils.copyProperties(record, recordDto);
                String sourceId = record.getSourceId();
                key = REDIS_HEAD + sourceId;
                statistics = handleCount(recordDto, key, mode, sourceId);
            } else if (StatisticsTypeEnum.ADD_COMMENT.getType().equals(mode)) {
                Object[] args = proceedingJoinPoint.getArgs();
                CommentDto comment = (CommentDto) args[0];
                String sourceId = comment.getSourceId();
                key = REDIS_HEAD + sourceId;
                statistics = handleCount(key, mode, sourceId);
            } else if (StatisticsTypeEnum.SUB_COMMENT.getType().equals(mode)) {
                Object[] args = proceedingJoinPoint.getArgs();
                String id = (String) args[0];
                List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>()
                        .eq(Comment::getId, id).or().eq(Comment::getPid, id));
                String sourceId = comments.get(0).getSourceId();
                key = REDIS_HEAD + sourceId;
                statistics = handleSubCount(key, comments.size(), sourceId);
            } else if (StatisticsTypeEnum.LIST.getType().equals(mode)) {
                List<ArticleDto> articleList = (List<ArticleDto>) proceedingJoinPoint.proceed();
                List<String> keys = articleList.stream()
                        .map(articleDto -> REDIS_HEAD + articleDto.getId())
                        .collect(Collectors.toList());
                List<Object> objects = redisUtil.mGetObjectByKey(keys);
                for (Object object : objects) {
                    if (object != null) {
                        Statistics s = (Statistics) object;
                        for (ArticleDto article : articleList) {
                            if (article.getId().equals(s.getSourceId())) {
                                article.setStatistics(s);
                            }
                        }
                    }
                }
                proceed = articleList;
            }
            // 异步任务数据存储到redis中
            if (statistics != null && key != null) {
                AsyncManager.me().execute(taskFactory.redisOperation(key, statistics, RedisOperationEnum.INSERT_UPDATE.getValue()));
                redisUtil.setKeyObject(key, statistics);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return proceed;
    }

    /**
     * 删除评论-评论数减少
     *
     * @param key      关键
     * @param number   数量
     * @param sourceId 源id
     * @return {@link Statistics}
     */
    private Statistics handleSubCount(String key, Integer number, String sourceId) {
        Object object = redisUtil.getObjectByKey(key);
        Statistics statistics;
        if (object != null) {
            statistics = (Statistics) object;
        } else {
            statistics = statisticsMapper.selectOne(new LambdaQueryWrapper<Statistics>()
                    .eq(Statistics::getSourceId, sourceId));
        }
        if (null != statistics) {
            statistics.setCommentCount(statistics.getCommentCount() - number);
        }
        return statistics;
    }

    /**
     * @param key      关键
     * @param mode     模式
     * @param sourceId 源id
     * @return {@link Statistics}
     */
    private Statistics handleCount(String key, String mode, String sourceId) {
        Object object = redisUtil.getObjectByKey(key);
        Statistics statistics;
        if (object != null) {
            statistics = (Statistics) object;
        } else {
            statistics = statisticsMapper.selectOne(new LambdaQueryWrapper<Statistics>()
                    .eq(Statistics::getSourceId, sourceId));
        }
        countNumber(statistics, mode);
        return statistics;
    }

    /**
     * 增加记录 - 增加对应的统计数据
     * 删除记录 - 减少对应的统计数据
     *
     * @param record   记录
     * @param key      关键
     * @param mode     模式
     * @param sourceId 源id
     * @return {@link Statistics}
     */
    private Statistics handleCount(RecordDto record, String key, String mode, String sourceId) {
        Object object = redisUtil.getObjectByKey(key);
        Statistics statistics;
        if (object != null) {
            statistics = (Statistics) object;
        } else {
            statistics = statisticsMapper.selectOne(new LambdaQueryWrapper<Statistics>()
                    .eq(Statistics::getSourceId, sourceId));
        }
        if (record.getType().equals(RecordTypeEnum.LIKE.getValue())) {
            mode = mode + RecordTypeEnum.LIKE.getName();
        } else if (record.getType().equals(RecordTypeEnum.COLLECT.getValue())) {
            mode = mode + RecordTypeEnum.COLLECT.getName();
        }
        if (null != statistics) {
            countNumber(statistics, mode);
        }
        return statistics;
    }

    /**
     * 查询动态统计数据
     *
     * @param dynamic 动态
     * @param key     关键
     * @param mode    模式
     * @return {@link Statistics}
     */
    private Statistics handleCount(DynamicDto dynamic, String key, String mode) {
        Object objectByKey = redisUtil.getObjectByKey(key);
        Statistics statistics;
        if (objectByKey == null) {
            statistics = dynamic.getStatistics();
            if (null == statistics) {
                statistics =initStatistics(dynamic.getId(), StatisticsEnum.DYNAMIC.getValue());
            }
            statistics.setSourceId(dynamic.getId());
        } else {
            statistics = (Statistics) objectByKey;
            dynamic.setStatistics((Statistics) objectByKey);
        }
        countNumber(statistics, mode);
        return statistics;
    }

    /**
     * 查询文章统计数据
     *
     * @param article 文章
     * @param key     关键
     * @param mode    模式
     * @return {@link Statistics}
     */
    private Statistics handleCount(ArticleDto article, String key, String mode) {
        Object objectByKey = redisUtil.getObjectByKey(key);
        Statistics statistics;
        if (objectByKey != null) {
            statistics = (Statistics) objectByKey;
            article.setStatistics((Statistics) objectByKey);
        } else {
            statistics = article.getStatistics();
            if (null == statistics) {
                statistics =initStatistics(article.getId(), StatisticsEnum.ARTICLE.getValue());
            }
            statistics.setSourceId(article.getId());
        }
        countNumber(statistics, mode);
        return statistics;
    }

    /**
     * 初始化数据
     *
     * @return {@link Statistics}
     */
    private Statistics initStatistics(String id, Integer type) {
        Statistics statistics = new Statistics();
        statistics.setCommentCount(0);
        statistics.setCollectCount(0);
        statistics.setViewCount(0);
        statistics.setLikeCount(0);
        statistics.setSourceType(type);
        statistics.setSourceId(id);
        return statistics;
    }

    /**
     * 根据mode进行不同的数据操作
     * 浏览数增加
     * 评论数增加-减少
     * 点赞数增加 -减少
     *
     * @param statistics 统计数据
     * @param mode       模式
     */
    private void countNumber(Statistics statistics, String mode) {
        if (mode.equals(StatisticsTypeEnum.ADD_VIEW.getType())) {
            statistics.setViewCount(statistics.getViewCount() + 1);
        } else if (mode.equals(StatisticsTypeEnum.ADD_COMMENT.getType())) {
            statistics.setCommentCount(statistics.getCommentCount() + 1);
        } else if (mode.equals(StatisticsTypeEnum.ADD_LIKE.getType())) {
            statistics.setLikeCount(statistics.getLikeCount() + 1);
        } else if (mode.equals(StatisticsTypeEnum.SUB_LIKE.getType())) {
            statistics.setLikeCount(statistics.getLikeCount() - 1);
        } else if (mode.equals(StatisticsTypeEnum.ADD_COLLECT.getType())) {
            statistics.setCollectCount(statistics.getCollectCount() + 1);
        } else if (mode.equals(StatisticsTypeEnum.SUB_COLLECT.getType())) {
            statistics.setCollectCount(statistics.getCollectCount() - 1);
        }
    }
}
