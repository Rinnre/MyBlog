package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.dto.LinkDto;
import com.wj.blog.pojo.entity.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 友链 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface LinkMapper extends BaseMapper<Link> {

    /**
     * 查询友链列表
     *
     * @param nickName    昵称
     * @param startNumber 开始条数
     * @param size        页面大小
     * @param status      状态
     * @return {@link List}<{@link LinkDto}>
     */
    List<LinkDto> selectLinkList(@Param("nickName") String nickName,
                                 @Param("status") Integer status,
                                 @Param("startNumber") Integer startNumber,
                                 @Param("size") Integer size);
}
