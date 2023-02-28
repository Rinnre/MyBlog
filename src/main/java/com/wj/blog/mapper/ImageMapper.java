package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 图片地址存储 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface ImageMapper extends BaseMapper<Image> {

    /**
     * 批量插入图片
     *
     * @param images 图片
     * @param type   类型
     * @param id     所属id
     */
    void insertBatch(@Param("images") List<Image> images, @Param("type") Integer type, @Param("id") String id);
}
