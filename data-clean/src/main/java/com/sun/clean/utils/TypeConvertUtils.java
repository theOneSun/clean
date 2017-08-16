package com.sun.clean.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @authur sunjian.
 */
public class TypeConvertUtils
{
    /**
     * 保留两位小数
     * @param value
     * @return
     */
    public static Double convertIntegerToDouble(Integer value){
        String s = value.toString();
        double doubleData = Double.parseDouble(s);//转成double
        DecimalFormat decimalFormat = new DecimalFormat("#.00");//保留两位小数
        String format = decimalFormat.format(doubleData);
        return Double.valueOf(format);
    }

    /**
     * 保留五位小数
     * @param value
     * @return
     */
    public static Double convertLongToDouble(Long value){
        String s = value.toString();
        double doubleData = Double.parseDouble(s);//转成double
        DecimalFormat decimalFormat = new DecimalFormat("#.00000");//保留五//位小数
        String format = decimalFormat.format(doubleData);
        return Double.valueOf(format);
    }

    public static Timestamp convertStringToTimestamp(String value) throws ParseException
    {
        //读取数据的格式
        DateFormat readFormat = new SimpleDateFormat("yyyyMMddHH");
        //目标日期的格式
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = readFormat.parse(value);
        String format = writeFormat.format(parse);
        return Timestamp.valueOf(format);
    }
}
