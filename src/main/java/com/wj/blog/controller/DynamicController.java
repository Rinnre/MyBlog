package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.common.util.CommentUtil;
import com.wj.blog.model.dto.DynamicDto;
import com.wj.blog.model.entity.Image;
import com.wj.blog.model.vo.*;
import com.wj.blog.service.DynamicService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/dynamic")
public class DynamicController {

    @Resource
    private DynamicService dynamicService;

    /**
     * 创建动态
     *
     * @param dynamicDto 动态dto
     * @return {@link ResultEntity}<{@link String}>
     */
    @PostMapping
    public ResultEntity<String> createDynamic(@RequestBody @Valid DynamicDto dynamicDto) {
        List<Image> imageList = dynamicDto.getImages();
        dynamicDto.setImages(imageList);
        dynamicService.createDynamic(dynamicDto);
        return ResultEntity.success();
    }

    /**
     * 删除动态
     *
     * @param uid uid
     * @param id  id
     * @return {@link ResultEntity}<{@link String}>
     */
    @DeleteMapping("/{uid}/{id}")
    public ResultEntity<String> removeDynamic(@PathVariable String uid, @PathVariable String id) {
        dynamicService.removeDynamic(uid, id);
        return ResultEntity.success();
    }

    /**
     * 查询动态
     *
     * @param userName 用户名
     * @param userId   用户id
     * @param content  内容
     * @param page     页面
     * @param size     大小
     * @return {@link ResultEntity}<{@link List}<{@link DynamicDetailVo}>>
     */
    @GetMapping
    public ResultEntity<List<DynamicDetailVo>> searchDynamicList(@RequestParam(required = false) String userName,
                                                                 @RequestParam(required = false) String userId,
                                                                 @RequestParam(required = false) String content,
                                                                 @RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) Integer size) {
        List<DynamicDto> dynamicDtoList = dynamicService.searchDynamicList(userName, userId, content, page, size);
        List<DynamicDetailVo> dynamicVoList = new ArrayList<>();
        convertDtoToVo(dynamicDtoList, dynamicVoList);
        return ResultEntity.success(dynamicVoList);
    }

    private void convertDtoToVo(List<DynamicDto> dynamicDtoList, List<DynamicDetailVo> dynamicVoList) {
        dynamicDtoList.forEach(dynamicDto -> {
            DynamicDetailVo dynamicDetailVo = new DynamicDetailVo();
            BeanUtils.copyProperties(dynamicDto, dynamicDetailVo);
            List<Image> images = dynamicDto.getImages();
            List<ImageVo> imageVoList = new ArrayList<>();
            if (null != images) {
                images.forEach(image -> {
                    ImageVo imageVo = new ImageVo();
                    BeanUtils.copyProperties(image, imageVo);
                    imageVoList.add(imageVo);
                });
            }
            StatisticsVo statisticsVo = new StatisticsVo();
            if (dynamicDto.getStatistics() != null) {
                BeanUtils.copyProperties(dynamicDto.getStatistics(), statisticsVo);
            }
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(dynamicDto.getUser(), userVo);
            //  comments->commentsVo 评论组装
            List<CommentDetailVo> commentVos = CommentUtil.assembleComment(dynamicDto.getCommentDtoList());

            dynamicDetailVo.setCommentVoList(commentVos);
            dynamicDetailVo.setUserVo(userVo);
            dynamicDetailVo.setImageVoList(imageVoList);
            dynamicDetailVo.setStatisticsVo(statisticsVo);
            dynamicVoList.add(dynamicDetailVo);
        });
    }


}
