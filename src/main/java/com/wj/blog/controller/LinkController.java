package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.dto.LinkDto;
import com.wj.blog.pojo.vo.LinkDetailVo;
import com.wj.blog.pojo.vo.LinkVo;
import com.wj.blog.service.LinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 友链 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
public class LinkController {

    @Resource(name = "linkService")
    private LinkService linkService;

    @GetMapping("/link")
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

    @PostMapping("/link")
    public ResultEntity<String> applyLink(@RequestBody LinkDetailVo linkDetailVo) {
        LinkDto linkDto = new LinkDto();
        BeanUtils.copyProperties(linkDetailVo, linkDto);
        linkService.applyLink(linkDto);
        return ResultEntity.success();
    }

    @DeleteMapping("/link/{id}/{uid}")
    public ResultEntity<String> removeLink(@PathVariable String id, @PathVariable String uid) {
        linkService.removeLink(id, uid);
        return ResultEntity.success();
    }

}
