package com.family.webserver.entity;

public class Cinema {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.id
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.district_id
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Integer districtId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.name
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.firstSpell
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private String firstspell;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.address
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private String address;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.latitude
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private String latitude;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.longitude
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private String longitude;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.pos
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Object pos;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.rating
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Float rating;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.has_3d
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Boolean has3d;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.has_imax
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Boolean hasImax;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.has_Wifi
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Boolean hasWifi;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.has_Park
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Boolean hasPark;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.tel
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private String tel;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.route
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private String route;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.id_maoyan
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Integer idMaoyan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cinema.id_mtime
     *
     * @mbggenerated Fri Feb 05 11:16:29 CST 2016
     */
    private Integer idMtime;

  /**
   * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cinema.id_baidu
     *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
     */
    private Integer idBaidu;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cinema.id_weixin
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  private Integer idWeixin;

  /**
     * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.id
   *
   * @return the value of cinema.id
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Integer getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cinema.id
     *
     * @param id the value for cinema.id
     *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.district_id
   *
   * @return the value of cinema.district_id
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Integer getDistrictId() {
    return districtId;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cinema.district_id
     *
     * @param districtId the value for cinema.district_id
     *
     * @mbggenerated Fri Feb 05 11:16:29 CST 2016
     */
    public void setDistrictId(Integer districtId) {
      this.districtId = districtId;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.name
   *
   * @return the value of cinema.name
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.name
   *
   * @param name the value for cinema.name
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.firstSpell
   *
   * @return the value of cinema.firstSpell
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public String getFirstspell() {
    return firstspell;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.firstSpell
   *
   * @param firstspell the value for cinema.firstSpell
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setFirstspell(String firstspell) {
    this.firstspell = firstspell == null ? null : firstspell.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.address
   *
   * @return the value of cinema.address
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public String getAddress() {
    return address;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.address
   *
   * @param address the value for cinema.address
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.latitude
   *
   * @return the value of cinema.latitude
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public String getLatitude() {
    return latitude;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.latitude
   *
   * @param latitude the value for cinema.latitude
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setLatitude(String latitude) {
    this.latitude = latitude == null ? null : latitude.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.longitude
   *
   * @return the value of cinema.longitude
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public String getLongitude() {
    return longitude;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.longitude
   *
   * @param longitude the value for cinema.longitude
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setLongitude(String longitude) {
    this.longitude = longitude == null ? null : longitude.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.pos
   *
   * @return the value of cinema.pos
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Object getPos() {
    return pos;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.pos
   *
   * @param pos the value for cinema.pos
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setPos(Object pos) {
    this.pos = pos;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.rating
   *
   * @return the value of cinema.rating
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Float getRating() {
    return rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.rating
   *
   * @param rating the value for cinema.rating
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setRating(Float rating) {
    this.rating = rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.has_3d
   *
   * @return the value of cinema.has_3d
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Boolean getHas3d() {
    return has3d;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.has_3d
   *
   * @param has3d the value for cinema.has_3d
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setHas3d(Boolean has3d) {
    this.has3d = has3d;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.has_imax
   *
   * @return the value of cinema.has_imax
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Boolean getHasImax() {
    return hasImax;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.has_imax
   *
   * @param hasImax the value for cinema.has_imax
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setHasImax(Boolean hasImax) {
    this.hasImax = hasImax;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.has_Wifi
   *
   * @return the value of cinema.has_Wifi
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Boolean getHasWifi() {
    return hasWifi;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.has_Wifi
   *
   * @param hasWifi the value for cinema.has_Wifi
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setHasWifi(Boolean hasWifi) {
    this.hasWifi = hasWifi;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.has_Park
   *
   * @return the value of cinema.has_Park
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Boolean getHasPark() {
    return hasPark;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.has_Park
   *
   * @param hasPark the value for cinema.has_Park
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setHasPark(Boolean hasPark) {
    this.hasPark = hasPark;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.tel
   *
   * @return the value of cinema.tel
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public String getTel() {
    return tel;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.tel
   *
   * @param tel the value for cinema.tel
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setTel(String tel) {
    this.tel = tel == null ? null : tel.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.route
   *
   * @return the value of cinema.route
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public String getRoute() {
    return route;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.route
   *
   * @param route the value for cinema.route
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setRoute(String route) {
    this.route = route == null ? null : route.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.id_maoyan
   *
   * @return the value of cinema.id_maoyan
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Integer getIdMaoyan() {
    return idMaoyan;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.id_maoyan
   *
   * @param idMaoyan the value for cinema.id_maoyan
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setIdMaoyan(Integer idMaoyan) {
    this.idMaoyan = idMaoyan;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.id_mtime
   *
   * @return the value of cinema.id_mtime
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Integer getIdMtime() {
    return idMtime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.id_mtime
   *
   * @param idMtime the value for cinema.id_mtime
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setIdMtime(Integer idMtime) {
    this.idMtime = idMtime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.id_baidu
   *
   * @return the value of cinema.id_baidu
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Integer getIdBaidu() {
    return idBaidu;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.id_baidu
   *
   * @param idBaidu the value for cinema.id_baidu
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setIdBaidu(Integer idBaidu) {
    this.idBaidu = idBaidu;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cinema.id_weixin
   *
   * @return the value of cinema.id_weixin
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public Integer getIdWeixin() {
    return idWeixin;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cinema.id_weixin
   *
   * @param idWeixin the value for cinema.id_weixin
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  public void setIdWeixin(Integer idWeixin) {
    this.idWeixin = idWeixin;
  }
}
