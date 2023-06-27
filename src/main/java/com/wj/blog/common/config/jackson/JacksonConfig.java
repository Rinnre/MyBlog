package com.wj.blog.common.config.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wj.blog.common.config.jackson.deserializer.DateDeSerializer;
import com.wj.blog.common.config.jackson.deserializer.LocalDateTimeDeserializer;
import com.wj.blog.common.config.jackson.serializer.DateSerializer;
import com.wj.blog.common.config.jackson.serializer.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;

import java.util.Date;


/**
 * Created by IntelliJ IDEA
 * 统一时间序列化配置
 * Controller层的接收与返回
 * @author wj
 * @date 2023/4/18 14:38
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper globalMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        SimpleModule localDateTimeModule = new SimpleModule();
        localDateTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer());
        localDateTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer());
        SimpleModule dateModule = new SimpleModule();
        dateModule.addSerializer(Date.class,new DateSerializer());
        dateModule.addDeserializer(Date.class,new DateDeSerializer());
        mapper.registerModules(localDateTimeModule,dateModule);
        return mapper;
    }
}
