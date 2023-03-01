package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 图片地址存储 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
public class ImageController {

    @Resource
    private ImageService imageService;

    @PostMapping("/file")
    public ResultEntity<String> uploadFile(MultipartFile file) {
        String url = imageService.uploadFile(file);
        return ResultEntity.success(url);
    }
}
