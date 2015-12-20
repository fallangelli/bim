package com.family.webserver.entity;

public class Source {

  Integer id;
  Integer sourceCount;
  String startTime;
  String endTime;
  Double salePrice;
  Double cinemaPrice;
  String source;
  String ticketURL;
  String language;
  String version;
  String hall;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSourceCount() {
    return sourceCount;
  }

  public void setSourceCount(Integer sourceCount) {
    this.sourceCount = sourceCount;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(Double salePrice) {
    this.salePrice = salePrice;
  }

  public Double getCinemaPrice() {
    return cinemaPrice;
  }

  public void setCinemaPrice(Double cinemaPrice) {
    this.cinemaPrice = cinemaPrice;
  }

  public String getSource() {

    if (source.compareToIgnoreCase("maoyan") == 0)
      return "猫眼";
    else if (source.compareToIgnoreCase("mtime") == 0)
      return "时光";
    else
      return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTicketURL() {
    return ticketURL;
  }

  public void setTicketURL(String ticketURL) {
    this.ticketURL = ticketURL;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getHall() {
    return hall;
  }

  public void setHall(String hall) {
    this.hall = hall;
  }
}
