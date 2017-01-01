package cn.mandroid.wechatrobot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wrBug on 2017/1/1.
 */

public class DateUtil {
    public static String dateyyyyMMDD(long mills) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(mills));
    }

    public static String dateyyyyMMDDHHmmss(long mills) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(mills));
    }
}