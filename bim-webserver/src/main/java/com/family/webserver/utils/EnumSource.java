package com.family.webserver.utils;

/**
 * Created by Administrator on 2016/2/3.
 */
public enum EnumSource {
  MTIME(1, "时光"), BAIDU(2, "百度"), WEIXIN(3, "微信"), MAOYAN(4, "猫眼");

  private Integer code;

  private String name;

  EnumSource(Integer code, String name) {
    this.code = code;
    this.name = name;
  }

  static public String getNameFromCode(Integer code) {
    EnumSource[] sources = EnumSource.values();
    for (EnumSource c : sources) {
      if (c.getCode() == code)
        return c.getName();
    }
    return null;
  }

  public Integer getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

}
