package com.wj.blog.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文章查询参数
 *
 * @author wj
 * @date 2023/02/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleQueryParam extends QueryParam{

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章所属分类")
    private String categoryId;

    @ApiModelProperty("文章发布作者")
    private String author;

}
