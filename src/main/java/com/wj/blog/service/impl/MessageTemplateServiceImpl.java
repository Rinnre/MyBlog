package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.exception.user.ParamIncorrectException;
import com.wj.blog.common.exception.user.ResourceNotExistException;
import com.wj.blog.mapper.MessageTemplateMapper;
import com.wj.blog.model.dto.MessageTemplateDto;
import com.wj.blog.model.param.MessageTemplateQueryParam;
import com.wj.blog.model.entity.MessageTemplate;
import com.wj.blog.service.MessageTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 消息模板 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service("messageTemplateService")
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {

    @Override
    public void modifyTemplate(MessageTemplateDto messageTemplateDto) {
        String id = messageTemplateDto.getId();
        if (Objects.equals(id, "") || null == id) {
            throw new ParamIncorrectException("消息模板id不能为空");
        }
        MessageTemplate messageTemplate = baseMapper.selectById(id);
        if (null == messageTemplate) {
            throw new ResourceNotExistException("模板不存在");
        }
        if (messageTemplateDto.getName() != null || !"".equals(messageTemplateDto.getName())) {
            messageTemplate.setName(messageTemplateDto.getName());
        }
        if (messageTemplateDto.getTitle() != null || !"".equals(messageTemplateDto.getTitle())) {
            messageTemplate.setTitle(messageTemplateDto.getTitle());
        }
        if (messageTemplateDto.getContent() != null || !"".equals(messageTemplateDto.getContent())) {
            messageTemplate.setContent(messageTemplateDto.getContent());
        }
        messageTemplate.setRemark(messageTemplateDto.getContent());

        baseMapper.updateById(messageTemplate);
    }

    @Override
    public List<MessageTemplateDto> searchMessageTemplateList(MessageTemplateQueryParam messageTemplateQueryParam) {
        // TODO 待完善
        return null;
    }
}
