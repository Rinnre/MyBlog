package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.model.dto.MessageTemplateDto;
import com.wj.blog.model.param.MessageTemplateQueryParam;
import com.wj.blog.service.MessageTemplateService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息模板
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/message_template")
public class MessageTemplateController {

    @Resource
    private MessageTemplateService messageTemplateService;

    /**
     * 创建模板
     *
     * @param messageTemplateDto 消息模板dto
     * @return {@link ResultEntity}<{@link String}>
     */
    @PostMapping
    public ResultEntity<String> createTemplate(@RequestBody @Validated MessageTemplateDto messageTemplateDto) {
        messageTemplateService.save(messageTemplateDto);
        return ResultEntity.success();
    }

    /**
     * 删除模板
     *
     * @param id 消息模板id
     * @return {@link ResultEntity}<{@link String}>
     */
    @DeleteMapping("/{id}")
    public ResultEntity<String> removeTemplate(@PathVariable String id) {
        messageTemplateService.removeById(id);
        return ResultEntity.success();
    }

    /**
     * 修改模板
     *
     * @param messageTemplateDto 消息模板dto
     * @return {@link ResultEntity}<{@link String}>
     */
    @PutMapping
    public ResultEntity<String> modifyTemplate(@RequestBody MessageTemplateDto messageTemplateDto) {
        messageTemplateService.modifyTemplate(messageTemplateDto);
        return ResultEntity.success();
    }

    /**
     * 查询信息模板列表
     *
     * @param messageTemplateQueryParam 消息模板查询参数
     * @return {@link ResultEntity}<{@link List}<{@link MessageTemplateDto}>>
     */
    @GetMapping
    public ResultEntity<List<MessageTemplateDto>> searchMessageTemplateList(
            @RequestParam MessageTemplateQueryParam messageTemplateQueryParam) {
        List<MessageTemplateDto> messageTemplateDtoList =
                messageTemplateService.searchMessageTemplateList(messageTemplateQueryParam);
        return ResultEntity.success(messageTemplateDtoList);
    }

}
