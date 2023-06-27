package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.model.dto.DynamicDto;
import com.wj.blog.model.entity.Dynamic;

import java.util.List;

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

    /**
     * 查询动态列表
     *
     * @param userName 用户名
     * @param userId   用户id
     * @param content  内容
     * @param page     页面
     * @param size     大小
     * @return {@link List}<{@link DynamicDto}>动态列表
     */
    List<DynamicDto> searchDynamicList(String userName, String userId, String content, Integer page, Integer size);

    /**
     * 删除动态
     *
     * @param uid 用户uid
     * @param id  动态id
     */
    void removeDynamic(String uid, String id);
}
