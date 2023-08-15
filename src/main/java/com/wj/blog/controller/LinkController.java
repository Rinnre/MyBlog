package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.model.dto.LinkDto;
import com.wj.blog.model.vo.LinkVo;
import com.wj.blog.service.LinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 友链
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/link")
public class LinkController {

    @Resource(name = "linkService")
    private LinkService linkService;

    /**
     * 查询友链列表
     *
     * @param nickName 昵称
     * @param page     页面
     * @param size     大小
     * @return {@link ResultEntity}<{@link List}<{@link LinkVo}>>
     */
    @GetMapping
    public ResultEntity<List<LinkVo>> searchLinkList(@RequestParam(required = false) String nickName,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        List<LinkDto> linkDtoList = linkService.searchLinkList(nickName, page, size);
        List<LinkVo> linkVoList = new ArrayList<>();
        linkDtoList.forEach(linkDto -> {
            LinkVo linkVo = new LinkVo();
            BeanUtils.copyProperties(linkDto, linkVo);
            linkVoList.add(linkVo);
        });
        return ResultEntity.success(linkVoList);
    }

    /**
     * 申请友链
     *
     * @param linkDto 链接dto
     * @return {@link ResultEntity}<{@link String}>
     */
    @PostMapping
    public ResultEntity<String> applyLink(@RequestBody @Validated LinkDto linkDto) {
        linkService.applyLink(linkDto);
        return ResultEntity.success();
    }

    /**
     * 删除友链
     *
     * @param id  友链id
     * @param uid 用户id
     * @return {@link ResultEntity}<{@link String}>
     */
    @DeleteMapping("/{id}/{uid}")
    public ResultEntity<String> removeLink(@PathVariable String id, @PathVariable String uid) {
        linkService.removeLink(id, uid);
        return ResultEntity.success();
    }

}
