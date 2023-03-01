package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.pojo.entity.Image;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片地址存储 服务类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface ImageService extends IService<Image> {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link String} 访问地址
     */
    String uploadFile(MultipartFile file);
}
