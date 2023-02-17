package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.RecordMapper;
import com.wj.blog.pojo.entity.Record;
import com.wj.blog.service.RecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞、收藏记录 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

}
