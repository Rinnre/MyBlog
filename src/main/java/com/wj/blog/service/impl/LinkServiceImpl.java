package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.enums.LinkStatusEnum;
import com.wj.blog.mapper.LinkMapper;
import com.wj.blog.model.dto.LinkDto;
import com.wj.blog.model.entity.Link;
import com.wj.blog.service.LinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public List<LinkDto> searchLinkList(String nickName, Integer page, Integer size) {
        Integer startNumber = null;
        if (page != null && size != null) {
            startNumber = (page - 1) * size;
        }
        return baseMapper.selectLinkList(nickName,LinkStatusEnum.NORMAL.getValue(),startNumber, size);
    }

    @Override
    public void applyLink(LinkDto linkDto) {
        // todo 需校验url有效性
        linkDto.setStatus(LinkStatusEnum.UNDER_REVIEW.getValue());
        Link link = new Link();
        BeanUtils.copyProperties(linkDto, link);
        // todo 生成通知后台审批
        link.setDelete(false);
        baseMapper.insert(link);
    }

    @Override
    public void removeLink(String id, String uid) {
        LambdaQueryWrapper<Link> linkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        linkLambdaQueryWrapper.eq(Link::getId, id).eq(Link::getUserId, uid);
        baseMapper.delete(linkLambdaQueryWrapper);
    }
}
