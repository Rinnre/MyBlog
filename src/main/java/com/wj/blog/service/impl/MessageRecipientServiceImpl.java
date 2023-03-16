package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.MessageRecipientMapper;
import com.wj.blog.pojo.entity.MessageRecipient;
import com.wj.blog.service.MessageRecipientService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息接收历史 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class MessageRecipientServiceImpl extends ServiceImpl<MessageRecipientMapper, MessageRecipient> implements MessageRecipientService {

}
