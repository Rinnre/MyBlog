package com.wj.blog.common.aop.annation;

import java.lang.annotation.*;

/**
 * 数量统计注解
 *
 * @author wj
 * @date 2023/03/08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NumberCount {
    String mode() default "";
}
