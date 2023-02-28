package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.pojo.dto.DynamicDto;
import com.wj.blog.pojo.entity.Dynamic;

/**
 * <p>
 * 动态表 服务类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface DynamicService extends IService<Dynamic> {

    /**
     * 创建动态
     *
     * @param dynamicDto 动态dto
     */
    void createDynamic(DynamicDto dynamicDto);
}
