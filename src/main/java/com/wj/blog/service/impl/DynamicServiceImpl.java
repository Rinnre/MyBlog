package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.enums.DynamicStatusEnum;
import com.wj.blog.common.enums.ImageEnum;
import com.wj.blog.common.enums.RedisOperationEnum;
import com.wj.blog.common.enums.StatisticsEnum;
import com.wj.blog.common.thread.AsyncManager;
import com.wj.blog.common.thread.TaskFactory;
import com.wj.blog.mapper.DynamicMapper;
import com.wj.blog.mapper.ImageMapper;
import com.wj.blog.mapper.StatisticsMapper;
import com.wj.blog.pojo.dto.DynamicDto;
import com.wj.blog.pojo.entity.Dynamic;
import com.wj.blog.pojo.entity.Image;
import com.wj.blog.pojo.entity.Statistics;
import com.wj.blog.service.DynamicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 动态表 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic> implements DynamicService {

    @Resource
    private StatisticsMapper statisticsMapper;

    @Resource
    private ImageMapper imageMapper;

    private static final String REDIS_HEAD = "statistics";
    @Resource
    private TaskFactory taskFactory;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createDynamic(DynamicDto dynamicDto) {
        // 动态信息保存
        Dynamic dynamic = new Dynamic();
        BeanUtils.copyProperties(dynamicDto, dynamic);
        // 初始化部分信息
        dynamic.setStatus(DynamicStatusEnum.NORMAL.getValue());
        baseMapper.insert(dynamic);

        // 初始化动态数据
        String id = dynamic.getId();
        Statistics statistics = new Statistics();
        statistics.setSourceId(id);
        statistics.setSourceType(StatisticsEnum.DYNAMIC.getValue());
        statisticsMapper.insert(statistics);
        // 异步初始化redis中的统计数据
        AsyncManager.me().execute(taskFactory.redisOperation(REDIS_HEAD + id, statistics, RedisOperationEnum.INSERT_UPDATE.getValue()));

        // 初始化图片数据
        List<Image> images = dynamicDto.getImages();
        images.forEach(image -> {
            image.setId(IdWorker.getIdStr());
            image.setCreateTime(LocalDateTime.now());
            image.setUpdateTime(LocalDateTime.now());
            image.setIsDelete(false);
        });

        // 保存动态图片
        imageMapper.insertBatch(images, ImageEnum.DYNAMIC.getValue(), id);

    }

    @Override
    public List<DynamicDto> searchDynamicList(String userName, String userId, String content, Integer page, Integer size) {
        Integer startNumber = null;
        if (page != null && size != null) {
            startNumber = (page - 1) * size;
        }
        return baseMapper.selectDynamicList(userName, userId, content, startNumber, size);
    }

    @Override
    public void removeDynamic(String uid, String id) {
        LambdaQueryWrapper<Dynamic> dynamicLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dynamicLambdaQueryWrapper.eq(Dynamic::getId, id);
        Dynamic dynamic = baseMapper.selectOne(dynamicLambdaQueryWrapper);
        if (dynamic.getUserId().equals(uid)) {
            baseMapper.deleteById(id);
            AsyncManager.me().execute(taskFactory.redisOperation(REDIS_HEAD + id, null, RedisOperationEnum.DELETE.getValue()));
        }
    }
}
