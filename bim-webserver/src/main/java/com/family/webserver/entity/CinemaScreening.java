package com.family.webserver.entity;

import java.util.List;

public class CinemaScreening {

  String movieName;
  String cinemaName;
  String showDate;

  List<Source> minSource;

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }

  public String getShowDate() {
    return showDate;
  }

  public void setShowDate(String showDate) {
    this.showDate = showDate;
  }

  public List<Source> getMinSource() {
    return minSource;
  }

  public void setMinSource(List<Source> minSource) {
    this.minSource = minSource;
  }
}
