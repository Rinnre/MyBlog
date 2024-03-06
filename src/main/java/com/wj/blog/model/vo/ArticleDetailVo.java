package com.wj.blog.model.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文章详细vo
 *
 * @author wj
 * {@code @date} 2023/02/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo extends ArticleIntroductionVo {
    @ApiModelProperty("作者id")
    private String userId;

    @ApiModelProperty("文章转载链接")
    private String sourceLink;

}
