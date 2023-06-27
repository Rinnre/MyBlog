package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.model.dto.LinkDto;
import com.wj.blog.model.entity.Link;

import java.util.List;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface LinkService extends IService<Link> {

    /**
     * 查询友链列表
     *
     * @param nickName 昵称
     * @param page     页数
     * @param size     大小
     * @return {@link List}<{@link LinkDto}> 友链列表
     */
    List<LinkDto> searchLinkList(String nickName, Integer page, Integer size);

    /**
     * 申请友链
     *
     * @param linkDto 友链dto
     */
    void applyLink(LinkDto linkDto);

    /**
     * 删除友链
     *
     * @param id  友链id
     * @param uid 用户uid
     */
    void removeLink(String id, String uid);
}
