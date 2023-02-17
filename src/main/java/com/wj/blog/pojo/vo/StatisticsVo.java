package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 统计Vo
 *
 * @author wj
 * @date 2023/02/17
 */
public class StatisticsVo {
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("点赞次数")
    private Integer likeCount;

    @ApiModelProperty("收藏次数")
    private Integer collectCount;

    @ApiModelProperty("评论次数")
    private Integer commentCount;

    @ApiModelProperty("浏览次数")
    private Integer viewCount;
}
