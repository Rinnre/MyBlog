package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.dto.DynamicDto;
import com.wj.blog.pojo.entity.Dynamic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 动态表 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface DynamicMapper extends BaseMapper<Dynamic> {

    /**
     * 查询动态列表
     *
     * @param userName    用户名
     * @param userId      用户id
     * @param content     内容
     * @param startNumber 开始数
     * @param size        大小
     * @return {@link List}<{@link DynamicDto}>
     */
    List<DynamicDto> selectDynamicList(@Param("userName") String userName,
                                       @Param("userId") String userId,
                                       @Param("content") String content,
                                       @Param("startNumber") Integer startNumber,
                                       @Param("size") Integer size);

}
