package com.wj.blog.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章查询参数
 *
 * @author wj
 * @date 2023/02/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleQueryParam {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章所属分类")
    private String categoryId;

    @ApiModelProperty("文章发布作者")
    private String author;

    @ApiModelProperty("文章所属标签")
    private List<String> tags;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("页码条数")
    private Integer size;

}
