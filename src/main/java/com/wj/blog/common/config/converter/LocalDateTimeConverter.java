package com.wj.blog.common.config.converter;

import com.wj.blog.common.enums.ErrorCodeEnum;
import com.wj.blog.common.exception.user.ParamIncorrectException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * 用于格式化@RequestParam LocalDateTime dateTime接收参数
 * 统一日期格式转换器
 *
 * @author wj
 * @date 2020/12/31 15:21
 */
@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER;

    static{
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    }

    @Override
    public LocalDateTime convert(String value) {
        if (value.isEmpty()){
            return null;
        }
        /// 转出为时间戳类型
        /*long timestamp = Long.parseLong(value);
        if (timestamp == 0) {
            return null;
        }
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();*/
        try {
            return LocalDateTime.parse(value, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParamIncorrectException(ErrorCodeEnum.W500, "日期参数格式不正确！");
        }
    }
}
