package com.wj.blog.model.dto;

import com.wj.blog.model.entity.Article;
import com.wj.blog.model.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章dto
 *
 * @author wj
 * {@code @date} 2023/02/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto extends Article {

    @ApiModelProperty("文章类别")
    private Category category;

    @ApiModelProperty("文章标签")
    private List<Category> tags;


    @ApiModelProperty("文章作者")
    private UserDto author;

    @ApiModelProperty("评论数量")
    private Integer commentCount;

}
