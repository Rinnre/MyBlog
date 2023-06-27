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
 * <p>
 * 消息模板 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/message_template")
public class MessageTemplateController {

    @Resource
    private MessageTemplateService messageTemplateService;

    @PostMapping
    public ResultEntity<String> createTemplate(@RequestBody @Validated MessageTemplateDto messageTemplateDto) {
        messageTemplateService.save(messageTemplateDto);
        return ResultEntity.success();
    }

    @DeleteMapping("/{id}")
    public ResultEntity<String> removeTemplate(@PathVariable String id) {
        messageTemplateService.removeById(id);
        return ResultEntity.success();
    }

    @PutMapping
    public ResultEntity<String> modifyTemplate(@RequestBody MessageTemplateDto messageTemplateDto) {
        messageTemplateService.modifyTemplate(messageTemplateDto);
        return ResultEntity.success();
    }

    @GetMapping
    public ResultEntity<List<MessageTemplateDto>> searchMessageTemplateList(
            @RequestParam MessageTemplateQueryParam messageTemplateQueryParam) {
        List<MessageTemplateDto> messageTemplateDtoList =
                messageTemplateService.searchMessageTemplateList(messageTemplateQueryParam);
        return ResultEntity.success(messageTemplateDtoList);
    }

}
