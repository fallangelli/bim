package com.family.webserver.entity;

public class Cityarea {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cityarea.id
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cityarea.city_id
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  private Integer cityId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column cityarea.name
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  private String name;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cityarea.id
   *
   * @return the value of cityarea.id
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public Integer getId() {
    return id;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cityarea.id
     *
     * @param id the value for cityarea.id
     *
     * @mbggenerated Tue Jan 26 17:14:54 CST 2016
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cityarea.city_id
   *
   * @return the value of cityarea.city_id
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public Integer getCityId() {
    return cityId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cityarea.city_id
   *
   * @param cityId the value for cityarea.city_id
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column cityarea.name
   *
   * @return the value of cityarea.name
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column cityarea.name
   *
   * @param name the value for cityarea.name
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }
}
