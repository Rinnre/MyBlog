package com.wj.blog.controller;


import com.wj.blog.common.enums.ErrorCodeEnum;
import com.wj.blog.common.exception.cdn.FileUploadException;
import com.wj.blog.common.result.Result;
import com.wj.blog.model.vo.ImageVo;
import com.wj.blog.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图片存储
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link Result}<{@link String}>
     */
    @PostMapping("/file")
    public Result<String> uploadFile(MultipartFile file) {
        try {
            String url = imageService.uploadFile(file);
            return Result.success(url);
        } catch (FileUploadException e) {
            return Result.fail(ErrorCodeEnum.W201.getCode(), e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@link Result}<{@link String}>
     */
    @DeleteMapping("/file")
    public Result<String> deleteFile(@RequestParam List<ImageVo> file) {
        imageService.deleteFile(file);
        return Result.success(null);
    }
}
