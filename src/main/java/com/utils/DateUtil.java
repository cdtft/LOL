package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by king on 2017/4/19.
 */
public class DateUtil {

    //日期转字符串
    public static String dateToStr(Date date){
        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        return str;
    }

    //字符串转日期
    public static Date strToDate(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
