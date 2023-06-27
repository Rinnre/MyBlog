package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.model.dto.RecordDto;
import com.wj.blog.model.vo.ArticleDetailVo;
import com.wj.blog.model.vo.DynamicVo;
import com.wj.blog.model.vo.RecordVo;
import com.wj.blog.model.vo.UserVo;
import com.wj.blog.service.RecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 点赞、收藏记录 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/blog/record")
public class RecordController {

    @Resource
    private RecordService recordService;

    @PostMapping
    public ResultEntity<String> addRecord(@RequestBody @Valid RecordDto recordDto) {
        recordService.addRecord(recordDto);
        return ResultEntity.success();
    }

    @GetMapping
    public ResultEntity<List<RecordVo>> searchRecordList(@RequestParam(required = false) String userId,
                                                         @RequestParam(required = false) String sourceId,
                                                         @RequestParam Integer type,
                                                         @RequestParam(required = false) Integer page,
                                                         @RequestParam(required = false) Integer size) {
        List<RecordDto> recordDtoList = recordService.searchRecordList(userId, sourceId, type, page, size);
        List<RecordVo> recordVoList = new ArrayList<>();
        recordDtoList.forEach(recordDto -> {
            RecordVo recordVo = new RecordVo();
            convertDtoToVo(recordDto, recordVo);
            recordVoList.add(recordVo);
        });
        return ResultEntity.success(recordVoList);
    }

    @DeleteMapping("/{uid}/{id}")
    public ResultEntity<String> removeRecord(@PathVariable String uid, @PathVariable String id) {
        recordService.removeRecord(id, uid);
        return ResultEntity.success();
    }


    private void convertDtoToVo(RecordDto recordDto, RecordVo recordVo) {
        // 基本数据
        BeanUtils.copyProperties(recordDto, recordVo);

        UserVo userVo = new UserVo();
        if (null != recordDto.getUser()) {
            BeanUtils.copyProperties(recordDto.getUser(), userVo);
        }
        recordVo.setUser(userVo);
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        if (null != recordDto.getArticle()) {
            BeanUtils.copyProperties(recordDto.getArticle(), articleDetailVo);
        }
        recordVo.setArticle(articleDetailVo);
        DynamicVo dynamicVo = new DynamicVo();
        if (null != recordDto.getDynamic()) {
            BeanUtils.copyProperties(recordDto.getDynamic(), dynamicVo);
        }
        recordVo.setDynamic(dynamicVo);
    }

}
