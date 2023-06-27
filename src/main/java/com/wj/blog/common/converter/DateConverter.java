package com.wj.blog.common.converter;

import com.wj.blog.common.exception.user.ParamIncorrectException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 统一日期格式转换器
 *
 * @author wj
 * @date 2020/12/31 15:21
 */
@Component
public class DateConverter implements Converter<String, Date> {

    private static SimpleDateFormat simpleDateFormat;

    static {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    }

    @Override
    public Date convert(String value) {
        /// 转为时间戳
        /*if (value.isEmpty()) {
            return null;
        }
        long timestamp = Long.parseLong(value);
        return new Date(timestamp);*/
        try {
            return simpleDateFormat.parse(value);
        } catch (ParseException e) {
            throw new ParamIncorrectException("日期参数格式不正确！");
        }
    }
}
