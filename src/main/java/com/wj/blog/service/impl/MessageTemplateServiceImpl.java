package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.MessageTemplateMapper;
import com.wj.blog.pojo.entity.MessageTemplate;
import com.wj.blog.service.MessageTemplateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息模板 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {

}
