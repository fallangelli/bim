package com.family.grabserver.util;

/**
 * Created by Administrator on 2016/2/3.
 */
public enum EnumType {
  all("all"), city("city"), cinema("cinema"), movieshowing("movieshowing"), screening("screening");


  private String name;

  EnumType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
