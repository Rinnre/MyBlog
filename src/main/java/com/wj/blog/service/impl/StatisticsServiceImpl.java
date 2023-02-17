package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.StatisticsMapper;
import com.wj.blog.pojo.entity.Statistics;
import com.wj.blog.service.StatisticsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章、动态数据统计 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {

}
