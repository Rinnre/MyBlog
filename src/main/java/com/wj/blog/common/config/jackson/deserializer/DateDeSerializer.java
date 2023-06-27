package com.wj.blog.common.config.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wj.blog.common.exception.user.ParamIncorrectException;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * 这个是全局通用的解序列化器，是被对应的序列化机制，
 * 如果需要从时间戳或者是字符串转为Date，需要自定反序列化机制并在对象中声明
 * 用于格式化JSON格式中日期类型的传参
 *
 * @author wj
 * @date 2020/12/31 17:40
 */
@JsonComponent
public class DateDeSerializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String time = jsonParser.readValueAs(String.class);
        if(null==time){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            throw new ParamIncorrectException("时间格式不正确！");
        }
    }
}
