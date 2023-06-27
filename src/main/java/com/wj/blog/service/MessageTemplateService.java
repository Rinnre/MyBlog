package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.model.dto.MessageTemplateDto;
import com.wj.blog.model.param.MessageTemplateQueryParam;
import com.wj.blog.model.entity.MessageTemplate;

import java.util.List;

/**
 * <p>
 * 消息模板 服务类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface MessageTemplateService extends IService<MessageTemplate> {

    /**
     * 修改模板
     *
     * @param messageTemplateDto 消息模板dto
     */
    void modifyTemplate(MessageTemplateDto messageTemplateDto);

    /**
     * 查询信息模板列表
     *
     * @param messageTemplateQueryParam 消息模板查询参数
     * @return {@link List}<{@link MessageTemplateDto}>
     */
    List<MessageTemplateDto> searchMessageTemplateList(MessageTemplateQueryParam messageTemplateQueryParam);
}
