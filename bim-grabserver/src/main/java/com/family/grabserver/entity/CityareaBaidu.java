package com.family.grabserver.entity;

public class CityareaBaidu {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cityarea_baidu.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cityarea_baidu.city_id
   *
   * @mbggenerated
   */
  private Integer cityId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cityarea_baidu.name
   *
   * @mbggenerated
   */
  private String name;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cityarea_baidu.id
   *
   * @return the value of cityarea_baidu.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cityarea_baidu.id
     *
     * @param id the value for cityarea_baidu.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cityarea_baidu.city_id
   *
   * @return the value of cityarea_baidu.city_id
   *
   * @mbggenerated
   */
  public Integer getCityId() {
    return cityId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cityarea_baidu.city_id
   *
   * @param cityId the value for cityarea_baidu.city_id
   *
   * @mbggenerated
   */
  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cityarea_baidu.name
   *
   * @return the value of cityarea_baidu.name
   *
   * @mbggenerated
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cityarea_baidu.name
   *
   * @param name the value for cityarea_baidu.name
   *
   * @mbggenerated
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }
}
