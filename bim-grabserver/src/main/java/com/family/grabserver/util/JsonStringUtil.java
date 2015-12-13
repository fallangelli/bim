package com.family.grabserver.util;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by Administrator on 2015/12/7.
 */
public class JsonStringUtil {
  public static String clearQuotation(String s) {
    char[] temp = s.toCharArray();
    int n = temp.length;
    for (int i = 0; i < n; i++) {
      if (temp[i] == ':' && temp[i + 1] == '"') {
        for (int j = i + 2; j < n; j++) {
          if (temp[j] == '"') {
            if (temp[j + 1] != ',' && temp[j + 1] != '}') {
              temp[j] = '”';
            } else if (temp[j + 1] == ',' || temp[j + 1] == '}') {
              break;
            }
          }
        }
      }
    }
    return new String(temp);
  }

  public static String toSplitString(JSONArray ja) {
    Object[] ig = ja.toArray();
    String str = "";
    if (ig != null && ig.length > 0) {
      for (int i = 0; i < ig.length; i++) {
        str += (String) ig[i] + ",";
      }
    }
    if (str.length() > 0)
      str = str.substring(0, str.length() - 1);
    return str;
  }
}
