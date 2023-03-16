package com.wj.blog.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置信息
 *
 * @author wj
 * @date 2023/01/06
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("blogApi")
                .apiInfo(blogApiInfo())
                .select()
                //只显示api路径下的页面
                .paths(PathSelectors.regex("/.*"))
                .build();

    }


    private ApiInfo blogApiInfo() {
        return new ApiInfoBuilder()
                .title("Blog-API文档")
                .description("本文档描述了个人博客接口定义")
                .version("1.0")
                .contact(new Contact("W", "http://localhost", "1595519431@qq.com"))
                .build();
    }




}
