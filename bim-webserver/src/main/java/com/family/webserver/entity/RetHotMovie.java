package com.family.webserver.entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by Administrator on 2015/12/12.
 */
@Alias("User")
public class RetHotMovie {

  Integer cityId;
  Integer movieId;
  String movieName;
  String image;
  String content;
  Float rating;
  Double minPrice;
  Integer screeningCount;

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public Double getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Double minPrice) {
    this.minPrice = minPrice;
  }

  public Integer getScreeningCount() {
    return screeningCount;
  }

  public void setScreeningCount(Integer screeningCount) {
    this.screeningCount = screeningCount;
  }
}
