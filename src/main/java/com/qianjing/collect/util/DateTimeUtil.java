package com.qianjing.collect.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {

    //hh 12小时制的，HH24小时制的
    private final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private final static String DATE = "yyyy-MM-dd";

    public static LocalDateTime parseDateTime(String str) {
        return LocalDateTime.parse(str);
    }

    public static LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_TIME);
    }

    public static String formatDate(LocalDate date) {
        return formatDate(date, DATE);
    }

    public static Date LocalDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime DateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
