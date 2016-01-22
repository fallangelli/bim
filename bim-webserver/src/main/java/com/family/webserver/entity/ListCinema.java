package com.family.webserver.entity;

import com.family.webserver.utils.Cn2Spell;

/**
 * Created by Administrator on 2015/12/13.
 */
public class ListCinema {
  Integer id;
  String name;
  String areaName;
  String address;
  String latitude;
  String longitude;
  String has3d;
  String hasImax;
  String tel;
  Double minPrice;
  Integer sourceCount;
  Double len;
  Integer districtId;
  String pinyin;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
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

  public Double getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Double minPrice) {
    this.minPrice = minPrice;
  }

  public Integer getSourceCount() {
    return sourceCount;
  }

  public void setSourceCount(Integer sourceCount) {
    this.sourceCount = sourceCount;
  }

  public Double getLen() {
    return len;
  }

  public void setLen(Double len) {
    this.len = len;
  }

  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer districtId) {
    this.districtId = districtId;
  }

  public String getPinyin() {
    return Cn2Spell.converterToFirstSpell(this.getName());
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }
}
