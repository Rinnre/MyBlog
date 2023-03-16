package com.wj.blog.common.config;

import com.wj.blog.common.interceptor.HttpInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wj
 * @descript
 * @date 2022/8/2 - 15:12
 */
@Configuration
@MapperScan("com.wj.blog.mapper")
@ComponentScan("com.wj.blog")
public class BlogConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new HttpInterceptor());
        //所有路径都被拦截
        registration.addPathPatterns("/**");
    }
}
