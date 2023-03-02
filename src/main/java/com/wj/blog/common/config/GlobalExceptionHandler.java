package com.wj.blog.common.config;

import com.wj.blog.common.result.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author wj
 * @date 2023/02/28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultEntity<String> exceptionHandler(Exception e) {
        log.error("GlobalExceptionHandler.exceptionHandler , 异常信息", e);
        return ResultEntity.fail("发生异常，请联系管理员");
    }


    /**
     * 处理所有RequestBody注解参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return ResultEntity.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 处理所有RequestParam注解数据验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResultEntity<String> handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.warn("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return ResultEntity.fail(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
