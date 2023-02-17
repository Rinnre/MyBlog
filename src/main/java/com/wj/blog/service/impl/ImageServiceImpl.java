package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.ImageMapper;
import com.wj.blog.pojo.entity.Image;
import com.wj.blog.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片地址存储 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

}
