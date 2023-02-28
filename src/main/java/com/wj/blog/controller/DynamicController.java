package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.dto.DynamicDto;
import com.wj.blog.pojo.dto.UserDto;
import com.wj.blog.pojo.entity.Image;
import com.wj.blog.pojo.vo.DynamicVo;
import com.wj.blog.pojo.vo.ImageVo;
import com.wj.blog.service.DynamicService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/blog")
public class DynamicController {

    @Resource
    private DynamicService dynamicService;

    @PostMapping("/dynamic")
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
        UserDto userDto = new UserDto();
        userDto.setId(dynamicVo.getUserId());
        dynamicDto.setUser(userDto);
        dynamicDto.setImages(imageList);
        dynamicService.createDynamic(dynamicDto);
        return ResultEntity.success();
    }
    /**
     * 删除动态
     */


    /**
     * 查询动态
     */

}
