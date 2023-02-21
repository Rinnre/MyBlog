package com.wj.blog.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章详细vo
 *
 * @author wj
 * @date 2023/02/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo extends ArticleIntroductionVo {
    @ApiModelProperty("作者id")
    private String userId;

    @ApiModelProperty("文章转载链接")
    private String sourceLink;

    @ApiModelProperty("文章评论")
    private List<CommentVo> comments;
}
