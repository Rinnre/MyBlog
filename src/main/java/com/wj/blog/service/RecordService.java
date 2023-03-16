package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.pojo.dto.RecordDto;
import com.wj.blog.pojo.entity.Record;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 点赞、收藏记录 服务类
 * </p>
 *
 * @author w
 * @since 2023-03-08
 */
@Service("recordService")
public interface RecordService extends IService<Record> {

    /**
     * 添加记录
     *
     * @param recordDto 记录dto
     */
    void addRecord(RecordDto recordDto);

    /**
     * 删除记录
     *
     * @param uid 用户id
     * @param id  记录id
     */
    void removeRecord(String id, String uid);

    /**
     * 查询记录列表
     *
     * @param userId   用户id
     * @param sourceId 所属id
     * @param type
     * @param page     页面
     * @param size     大小
     * @return {@link List}<{@link RecordDto}>
     */
    List<RecordDto> searchRecordList(String userId, String sourceId, Integer type, Integer page, Integer size);
}
