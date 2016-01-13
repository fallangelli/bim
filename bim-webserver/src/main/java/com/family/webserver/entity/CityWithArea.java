package com.family.webserver.entity;

import java.util.List;

public class CityWithArea {

  Integer id;
  List<CityArea> areas;
  private String firstLetter;
  private String name;
  private String pinyin;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstLetter() {
    return firstLetter;
  }

  public void setFirstLetter(String firstLetter) {
    this.firstLetter = firstLetter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPinyin() {
    return pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  public List<CityArea> getAreas() {
    return areas;
  }

  public void setAreas(List<CityArea> areas) {
    this.areas = areas;
  }
}
