package guo.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//类型转换器 string转date
public class ZdyDate implements Converter<String,Date> {
    @Override
    public Date convert(String s){
        if(s==null){
            throw new RuntimeException("传入日期为空");
        }
        DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
           return simpleDateFormat.parse(s);
        }catch (ParseException e){
            throw new RuntimeException("传入格式错误");
        }
    }
}

