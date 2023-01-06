package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.Tag;
import com.wj.blog.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/blog/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/getTags")
    @ApiOperation("查询所有Tags")
    public ResultEntity<List<Tag>> getAllTag(){
        return ResultEntity.success(tagService.getBaseMapper().selectList(null));
    }

    @GetMapping("getTags/{articleId}")
    @ApiOperation("根据文章id查询tag")
    public ResultEntity<List<Tag>> getTagsByArticleId(@PathVariable String articleId){
        List<Tag> tagList = tagService.getTagsByArticleId(articleId);
        return ResultEntity.success(tagList);
    }

    @PostMapping("/addTag")
    @ApiOperation("添加tag")
    public ResultEntity<String> addTag(@RequestBody Tag tag){
        boolean insertResult = tagService.save(tag);
        if(insertResult){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }

    @DeleteMapping("/removeTag/{tagId}")
    @ApiOperation("删除tag")
    public ResultEntity<String> removeTag(@PathVariable String tagId){
        boolean deleteResult = tagService.removeById(tagId);
        if(deleteResult){
            return ResultEntity.success();
        }else{
            return ResultEntity.fail();
        }
    }
}

