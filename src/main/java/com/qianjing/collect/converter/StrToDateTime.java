package com.qianjing.collect.converter;

import com.qianjing.collect.util.DateTimeUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StrToDateTime implements Converter<String, LocalDateTime>{
    @Override
    public LocalDateTime convert(String str) {
        return DateTimeUtil.parseDateTime(str);
    }
}
