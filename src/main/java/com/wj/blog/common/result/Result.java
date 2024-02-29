package com.wj.blog.common.result;

import com.wj.blog.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 *
 * @author wj
 * {@code @date} 2023/01/06
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    @ApiModelProperty(value = "请求时间")
    private Long timestamp;


    /**
     * 操作成功
     *
     * @param data 操作结果
     * @return {@link Result}<{@link T}> 统一返回封装对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ErrorCodeEnum.W200.getCode());
        result.setMessage(ErrorCodeEnum.W200.getMessage());
        result.setData(data);
        return result;
    }


    /**
     * 操作失败
     *
     * @param code    错误码
     * @param message 错误信息
     * @return {@link Result}<{@link T}> 统一返回封装对象
     */
    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(ErrorCodeEnum.W201.getCode());
        result.setMessage(ErrorCodeEnum.W201.getMessage());
        return result;
    }

}
