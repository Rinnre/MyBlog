package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.model.dto.RecordDto;
import com.wj.blog.model.entity.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 点赞、收藏记录 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-03-08
 */
public interface RecordMapper extends BaseMapper<Record> {

    /**
     * 选择记录列表
     *
     * @param userId      用户id
     * @param sourceId    所属id
     * @param type        记录类型
     * @param startNumber 开始条数
     * @param size        大小
     * @return {@link List}<{@link RecordDto}>
     */
    List<RecordDto> selectRecordList(@Param("userId") String userId,
                                     @Param("sourceId") String sourceId,
                                     @Param("type") Integer type,
                                     @Param("startNumber") Integer startNumber,
                                     @Param("size") Integer size);

}
