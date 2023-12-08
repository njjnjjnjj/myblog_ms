package com.njj.blog.common.util.common.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 倪佳俊
 * @date 2023/2/12
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String DATE_FORMAT_DATE = "yyyyMMdd";

    public static String DATE_FORMAT_DATE_SPLIT_WITH_SEPARATOR = "yyyy-MM-dd";

    public static String DATE_FORMAT_DATETIME  = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_FORMAT_DATETIME_SIMPLE = "yyyyMMddHHmmss";

    /**
     * 格式化当前时间
     *
     * @param format 指定时间格式，可使用DateUtils中的常量，也可自定义
     * @return 返还格式化后的时间
     */
    public static String formatCurrentDate(String format){
        return formatDate(new Date(), format);
    }

    /**
     * 格式化时间
     *
     * @param date 时间
     * @param format 指定时间格式
     * @return 返还格式化后的时间
     */
    public static String formatDate(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
