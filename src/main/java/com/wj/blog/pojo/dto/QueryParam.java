package com.wj.blog.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA
 *
 * @author wj
 * @date 2023/3/24 17:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryParam {
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("页码条数")
    private Integer size;
}
