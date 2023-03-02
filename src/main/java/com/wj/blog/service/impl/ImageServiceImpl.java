package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.config.OssProperties;
import com.wj.blog.common.util.OssUtil;
import com.wj.blog.mapper.ImageMapper;
import com.wj.blog.pojo.entity.Image;
import com.wj.blog.pojo.vo.ImageVo;
import com.wj.blog.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private OssProperties ossProperties;

    @Override
    public String uploadFile(MultipartFile file) {
        return OssUtil.fileUpload(file, ossProperties);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFile(List<ImageVo> file) {
        List<String> fileIds = file.stream().map(ImageVo::getId).collect(Collectors.toList());
        List<String> fileName = file.stream().map(ImageVo::getUrl).collect(Collectors.toList());
        OssUtil.fileDelete(fileName, ossProperties);
        baseMapper.deleteBatchIds(fileIds);
    }
}
