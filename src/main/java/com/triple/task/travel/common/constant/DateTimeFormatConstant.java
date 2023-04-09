package com.triple.task.travel.common.constant;

import java.time.format.DateTimeFormatter;

public final class DateTimeFormatConstant {
    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public final static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    public final static DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);

}
