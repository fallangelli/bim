package com.family.webserver.entity;

import com.family.webserver.utils.EnumSource;

/**
 * Created by Administrator on 2016/2/5.
 */
public class RetSource {

  Integer id;
  Integer sourceCount;
  String startTime;
  String endTime;
  Double salePrice;
  Double cinemaPrice;
  Integer sourceId;
  String ticketURL;
  String language;
  String version;
  String hall;
  String source;

  public String getSource() {
    return EnumSource.getNameFromCode(this.getSourceId());
  }

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

  public Integer getSourceId() {
    return sourceId;
  }

  public void setSourceId(Integer sourceId) {
    this.sourceId = sourceId;
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
