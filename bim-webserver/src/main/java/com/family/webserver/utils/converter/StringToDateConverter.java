package com.family.webserver.utils.converter;

import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

  @Override
  public Date convert(String strDate) {
    if (!StringUtils.isNullOrEmpty(strDate)) {
      try {
        if (strDate.length() <= 5) {
          DateFormat df = new SimpleDateFormat("HH:mm");
          return df.parse(strDate);
        }
        if (strDate.length() <= 8) {
          DateFormat df = new SimpleDateFormat("HH:mm:ss");
          return df.parse(strDate);
        } else if (strDate.length() <= 10 && strDate.length() > 8) {
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          return df.parse(strDate);
        } else if (strDate.length() <= 16 && strDate.length() > 10) {
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          strDate += ":00";
          return df.parse(strDate);
        } else if (strDate.length() <= 19 && strDate.length() > 16) {
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          strDate += ":00";
          return df.parse(strDate);
        } else {
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          return df.parse(strDate);
        }
      } catch (ParseException e) {
        Logger.getLogger(this.getClass())
          .error(String.format("日期时间到字符串类型转换失败-[%s]", strDate));
        throw new IllegalArgumentException(String.format("日期时间到字符串类型转换失败-[%s]", strDate));
      }

    }
    return null;
  }
}
