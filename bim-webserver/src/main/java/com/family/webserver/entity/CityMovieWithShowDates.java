package com.family.webserver.entity;

import java.util.List;

public class CityMovieWithShowDates {

  Integer distinctId;
  List<String> showDates;

  public Integer getDistinctId() {
    return distinctId;
  }

  public void setDistinctId(Integer distinctId) {
    this.distinctId = distinctId;
  }

  public List<String> getShowDates() {
    return showDates;
  }

  public void setShowDates(List<String> showDates) {
    this.showDates = showDates;
  }
}
