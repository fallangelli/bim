package com.family.grabserver.util;

/**
 * Created by Administrator on 2016/2/3.
 */
public enum EnumSource {
  MTIME(1), BAIDU(2), WEIXIN(3), MAOYAN(4);

  private Integer code;

  EnumSource(Integer code) {
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
