package com.wj.blog.common.config.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA
 *
 * 统一localDateTime解析
 * @author wj
 * @date 2020/12/31 14:18
 */
@JsonComponent
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {


    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        gen.writeObject(value.format(dateTimeFormatter));
    }

}
