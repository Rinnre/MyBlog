package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.MessageHistoryMapper;
import com.wj.blog.model.entity.MessageHistory;
import com.wj.blog.service.MessageHistoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息历史 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class MessageHistoryServiceImpl extends ServiceImpl<MessageHistoryMapper, MessageHistory> implements MessageHistoryService {

}
