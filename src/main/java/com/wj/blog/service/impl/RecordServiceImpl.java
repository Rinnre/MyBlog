package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.aop.annation.NumberCount;
import com.wj.blog.mapper.RecordMapper;
import com.wj.blog.model.dto.RecordDto;
import com.wj.blog.model.entity.Record;
import com.wj.blog.service.RecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 点赞、收藏记录 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-03-08
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @NumberCount(mode = "add")
    @Override
    public void addRecord(RecordDto recordDto) {
        Record record = new Record();
        BeanUtils.copyProperties(recordDto, record);
        baseMapper.insert(record);
    }

    @NumberCount(mode = "sub")
    @Override
    public void removeRecord(String id, String uid) {
        Record record = baseMapper.selectById(id);
        if (record == null) {
            // 资源不存在
            throw new RuntimeException("资源不存在");
        }
        if (!uid.equals(record.getUserId())) {
            // 删除失败
        }
        baseMapper.deleteById(id);
    }

    @Override
    public List<RecordDto> searchRecordList(String userId, String sourceId, Integer type, Integer page, Integer size) {
        Integer startNumber = null;
        if (page != null && size != null) {
            startNumber = (page - 1) * size;
        }
        return baseMapper.selectRecordList(userId, sourceId, type, startNumber, size);
    }
}
