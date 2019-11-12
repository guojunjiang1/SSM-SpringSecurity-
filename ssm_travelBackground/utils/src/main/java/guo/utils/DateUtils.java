package guo.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils {
    //日期转字符串
    public static String DateToString(Date date,String pen){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pen);
        String format = simpleDateFormat.format(date);
        return format;
    }
    //字符串转日期
    public static Date StringToDate(String date,String pen) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pen);
        Date parse = simpleDateFormat.parse(date);
        return parse;
    }
}
