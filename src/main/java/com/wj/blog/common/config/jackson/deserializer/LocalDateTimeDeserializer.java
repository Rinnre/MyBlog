package com.wj.blog.common.config.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wj.blog.common.exception.user.ParamIncorrectException;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA
 * 这个是全局通用的解序列化器，是被对应的序列化机制，
 * 如果需要从时间戳或者是字符串转为localDataTime，需要自定反序列化机制并在对象中声明
 * 用于格式化JSON格式中日期类型的传参
 *
 * @author wj
 * @date 2020/12/31 14:18
 */
@JsonComponent
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        String time = jsonParser.readValueAs(String.class);
        if (null == time) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        try {
            return LocalDateTime.parse(time,dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new ParamIncorrectException("日期参数格式不正确！");
        }
    }

}
