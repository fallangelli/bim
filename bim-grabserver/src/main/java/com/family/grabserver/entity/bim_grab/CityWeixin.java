package com.family.grabserver.entity.bim_grab;

public class CityWeixin {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..city_weixin.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..city_weixin.name
   *
   * @mbggenerated
   */
  private String name;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..city_weixin.id
   *
   * @return the value of bim_grab..city_weixin.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bim_grab..city_weixin.id
     *
     * @param id the value for bim_grab..city_weixin.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..city_weixin.name
   *
   * @return the value of bim_grab..city_weixin.name
   *
   * @mbggenerated
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..city_weixin.name
   *
   * @param name the value for bim_grab..city_weixin.name
   *
   * @mbggenerated
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }
}
