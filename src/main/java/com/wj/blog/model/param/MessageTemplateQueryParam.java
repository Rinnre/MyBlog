package com.wj.blog.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 *
 * @author wj
 * @date 2023/3/24 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTemplateQueryParam extends QueryParam {
    @ApiModelProperty("模板标题")
    private String title;

    @ApiModelProperty("模板名称")
    private String name;

}
