package com.family.webserver.entity;

import java.util.List;

public class CityMovieWithShowDates {

  String movieName;
  List<String> showDates;

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public List<String> getShowDates() {
    return showDates;
  }

  public void setShowDates(List<String> showDates) {
    this.showDates = showDates;
  }
}
