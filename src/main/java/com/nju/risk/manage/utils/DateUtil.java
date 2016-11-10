package com.nju.risk.manage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: winsky
 * date: 2016/11/7
 * description:
 */
public class DateUtil {
    /**
     * 静态工具类，外部需要构造对象
     */
    private DateUtil() {
    }

    public static String formatDate(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
