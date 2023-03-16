package com.wj.blog.controller;


import com.wj.blog.common.exception.cdn.FileUploadException;
import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.vo.ImageVo;
import com.wj.blog.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

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
        try {
            String url = imageService.uploadFile(file);
            return ResultEntity.success(url);
        } catch (FileUploadException e) {
            return ResultEntity.fail(e.getMessage());
        }
    }

    @DeleteMapping("/file")
    public ResultEntity<String> deleteFile(@RequestParam List<ImageVo> file) {
        imageService.deleteFile(file);
        return ResultEntity.success();
    }
}
