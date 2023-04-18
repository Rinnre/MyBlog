package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.common.util.CommentUtil;
import com.wj.blog.pojo.dto.DynamicDto;
import com.wj.blog.pojo.entity.Image;
import com.wj.blog.pojo.vo.*;
import com.wj.blog.service.DynamicService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 动态表 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/dynamic")
public class DynamicController {

    @Resource
    private DynamicService dynamicService;

    @PostMapping
    public ResultEntity<String> createDynamic(@RequestBody @Valid DynamicVo dynamicVo) {
        DynamicDto dynamicDto = new DynamicDto();
        BeanUtils.copyProperties(dynamicVo, dynamicDto);
        List<ImageVo> imageVoList = dynamicVo.getImageVoList();
        List<Image> imageList = new ArrayList<>();
        if (imageVoList != null) {
            imageVoList.forEach(imageVo -> {
                Image image = new Image();
                BeanUtils.copyProperties(imageVo, image);
                imageList.add(image);
            });
        }
        dynamicDto.setImages(imageList);
        dynamicService.createDynamic(dynamicDto);
        return ResultEntity.success();
    }

    /**
     * 删除动态
     */
    @DeleteMapping("/{uid}/{id}")
    public ResultEntity<String> removeDynamic(@PathVariable String uid, @PathVariable String id) {
        dynamicService.removeDynamic(uid, id);
        return ResultEntity.success();
    }

    /**
     * 查询动态
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
