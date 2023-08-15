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
 * 点赞、收藏记录
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

    /**
     * 查询记录列表
     *
     * @param userId   用户id
     * @param sourceId 源id
     * @param type     类型
     * @param page     页面
     * @param size     大小
     * @return {@link ResultEntity}<{@link List}<{@link RecordVo}>>
     */
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

    /**
     * 删除记录
     *
     * @param uid 用户id
     * @param id  记录id
     * @return {@link ResultEntity}<{@link String}>
     */
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
