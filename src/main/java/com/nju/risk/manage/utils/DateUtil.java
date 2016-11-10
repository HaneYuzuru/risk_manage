package com.nju.risk.manage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        String ret = simpleDateFormat.format(date);
        // 2016-11-10 13:07:55 CST
        return ret.replace(" CST","");
    }
}
