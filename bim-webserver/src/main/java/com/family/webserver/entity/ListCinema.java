package com.family.webserver.entity;

/**
 * Created by Administrator on 2015/12/13.
 */
public class ListCinema {
  Integer id;
  String area;
  String name;
  String address;
  String latitude;
  String longitude;
  String has3d;
  String hasImax;
  String tel;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getHasImax() {
    return hasImax;
  }

  public void setHasImax(String hasImax) {
    this.hasImax = hasImax;
  }

  public String getHas3d() {
    return has3d;
  }

  public void setHas3d(String has3d) {
    this.has3d = has3d;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }
}
