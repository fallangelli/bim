package com.family.webserver.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/12/13.
 */
public class RetListCity {
  String firstLetter;
  List<City> cities;

  public String getFirstLetter() {
    return firstLetter;
  }

  public void setFirstLetter(String firstLetter) {
    this.firstLetter = firstLetter;
  }

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }
}
