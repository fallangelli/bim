package com.family.grabserver.entity.bim_base;

public class Cinema {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.district_id
   *
   * @mbggenerated
   */
  private Integer districtId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.name
   *
   * @mbggenerated
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.address
   *
   * @mbggenerated
   */
  private String address;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.latitude
   *
   * @mbggenerated
   */
  private String latitude;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.longitude
   *
   * @mbggenerated
   */
  private String longitude;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.pos
   *
   * @mbggenerated
   */
  private Object pos;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.rating
   *
   * @mbggenerated
   */
  private Double rating;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.has_3d
   *
   * @mbggenerated
   */
  private Boolean has3d;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.has_imax
   *
   * @mbggenerated
   */
  private Boolean hasImax;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.has_Wifi
   *
   * @mbggenerated
   */
  private Boolean hasWifi;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.has_Park
   *
   * @mbggenerated
   */
  private Boolean hasPark;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.tel
   *
   * @mbggenerated
   */
  private String tel;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.route
   *
   * @mbggenerated
   */
  private String route;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.id_maoyan
   *
   * @mbggenerated
   */
  private Integer idMaoyan;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.id_mtime
   *
   * @mbggenerated
   */
  private Integer idMtime;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..cinema.id_baidu
   *
   * @mbggenerated
   */
  private Integer idBaidu;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.id
   *
   * @return the value of bim_base..cinema.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bim_base..cinema.id
     *
     * @param id the value for bim_base..cinema.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.district_id
   *
   * @return the value of bim_base..cinema.district_id
   *
   * @mbggenerated
   */
  public Integer getDistrictId() {
    return districtId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.district_id
   *
   * @param districtId the value for bim_base..cinema.district_id
   *
   * @mbggenerated
   */
  public void setDistrictId(Integer districtId) {
    this.districtId = districtId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.name
   *
   * @return the value of bim_base..cinema.name
   *
   * @mbggenerated
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.name
   *
   * @param name the value for bim_base..cinema.name
   *
   * @mbggenerated
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.address
   *
   * @return the value of bim_base..cinema.address
   *
   * @mbggenerated
   */
  public String getAddress() {
    return address;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.address
   *
   * @param address the value for bim_base..cinema.address
   *
   * @mbggenerated
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.latitude
   *
   * @return the value of bim_base..cinema.latitude
   *
   * @mbggenerated
   */
  public String getLatitude() {
    return latitude;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.latitude
   *
   * @param latitude the value for bim_base..cinema.latitude
   *
   * @mbggenerated
   */
  public void setLatitude(String latitude) {
    this.latitude = latitude == null ? null : latitude.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.longitude
   *
   * @return the value of bim_base..cinema.longitude
   *
   * @mbggenerated
   */
  public String getLongitude() {
    return longitude;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.longitude
   *
   * @param longitude the value for bim_base..cinema.longitude
   *
   * @mbggenerated
   */
  public void setLongitude(String longitude) {
    this.longitude = longitude == null ? null : longitude.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.pos
   *
   * @return the value of bim_base..cinema.pos
   *
   * @mbggenerated
   */
  public Object getPos() {
    return pos;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.pos
   *
   * @param pos the value for bim_base..cinema.pos
   *
   * @mbggenerated
   */
  public void setPos(Object pos) {
    this.pos = pos;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.rating
   *
   * @return the value of bim_base..cinema.rating
   *
   * @mbggenerated
   */
  public Double getRating() {
    return rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.rating
   *
   * @param rating the value for bim_base..cinema.rating
   *
   * @mbggenerated
   */
  public void setRating(Double rating) {
    this.rating = rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.has_3d
   *
   * @return the value of bim_base..cinema.has_3d
   *
   * @mbggenerated
   */
  public Boolean getHas3d() {
    return has3d;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.has_3d
   *
   * @param has3d the value for bim_base..cinema.has_3d
   *
   * @mbggenerated
   */
  public void setHas3d(Boolean has3d) {
    this.has3d = has3d;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.has_imax
   *
   * @return the value of bim_base..cinema.has_imax
   *
   * @mbggenerated
   */
  public Boolean getHasImax() {
    return hasImax;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.has_imax
   *
   * @param hasImax the value for bim_base..cinema.has_imax
   *
   * @mbggenerated
   */
  public void setHasImax(Boolean hasImax) {
    this.hasImax = hasImax;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.has_Wifi
   *
   * @return the value of bim_base..cinema.has_Wifi
   *
   * @mbggenerated
   */
  public Boolean getHasWifi() {
    return hasWifi;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.has_Wifi
   *
   * @param hasWifi the value for bim_base..cinema.has_Wifi
   *
   * @mbggenerated
   */
  public void setHasWifi(Boolean hasWifi) {
    this.hasWifi = hasWifi;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.has_Park
   *
   * @return the value of bim_base..cinema.has_Park
   *
   * @mbggenerated
   */
  public Boolean getHasPark() {
    return hasPark;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.has_Park
   *
   * @param hasPark the value for bim_base..cinema.has_Park
   *
   * @mbggenerated
   */
  public void setHasPark(Boolean hasPark) {
    this.hasPark = hasPark;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.tel
   *
   * @return the value of bim_base..cinema.tel
   *
   * @mbggenerated
   */
  public String getTel() {
    return tel;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.tel
   *
   * @param tel the value for bim_base..cinema.tel
   *
   * @mbggenerated
   */
  public void setTel(String tel) {
    this.tel = tel == null ? null : tel.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.route
   *
   * @return the value of bim_base..cinema.route
   *
   * @mbggenerated
   */
  public String getRoute() {
    return route;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.route
   *
   * @param route the value for bim_base..cinema.route
   *
   * @mbggenerated
   */
  public void setRoute(String route) {
    this.route = route == null ? null : route.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.id_maoyan
   *
   * @return the value of bim_base..cinema.id_maoyan
   *
   * @mbggenerated
   */
  public Integer getIdMaoyan() {
    return idMaoyan;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.id_maoyan
   *
   * @param idMaoyan the value for bim_base..cinema.id_maoyan
   *
   * @mbggenerated
   */
  public void setIdMaoyan(Integer idMaoyan) {
    this.idMaoyan = idMaoyan;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.id_mtime
   *
   * @return the value of bim_base..cinema.id_mtime
   *
   * @mbggenerated
   */
  public Integer getIdMtime() {
    return idMtime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.id_mtime
   *
   * @param idMtime the value for bim_base..cinema.id_mtime
   *
   * @mbggenerated
   */
  public void setIdMtime(Integer idMtime) {
    this.idMtime = idMtime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..cinema.id_baidu
   *
   * @return the value of bim_base..cinema.id_baidu
   *
   * @mbggenerated
   */
  public Integer getIdBaidu() {
    return idBaidu;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..cinema.id_baidu
   *
   * @param idBaidu the value for bim_base..cinema.id_baidu
   *
   * @mbggenerated
   */
  public void setIdBaidu(Integer idBaidu) {
    this.idBaidu = idBaidu;
  }
}
