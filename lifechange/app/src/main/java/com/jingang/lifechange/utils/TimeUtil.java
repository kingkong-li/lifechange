package com.jingang.lifechange.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    public static String formatTimestamp(long timestamp) {
        // "yyyy-MM-dd" 仅年月日
        // "yyyy-MM-dd HH:mm:ss" 年月日时分秒
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}