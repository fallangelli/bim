package com.family.grabserver.entity.bim_grab;

public class CityTaobao {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..city_taobao.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..city_taobao.name
   *
   * @mbggenerated
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..city_taobao.first_letter
   *
   * @mbggenerated
   */
  private String firstLetter;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..city_taobao.pinyin
   *
   * @mbggenerated
   */
  private String pinyin;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..city_taobao.id
   *
   * @return the value of bim_grab..city_taobao.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bim_grab..city_taobao.id
     *
     * @param id the value for bim_grab..city_taobao.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..city_taobao.name
   *
   * @return the value of bim_grab..city_taobao.name
   *
   * @mbggenerated
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..city_taobao.name
   *
   * @param name the value for bim_grab..city_taobao.name
   *
   * @mbggenerated
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..city_taobao.first_letter
   *
   * @return the value of bim_grab..city_taobao.first_letter
   *
   * @mbggenerated
   */
  public String getFirstLetter() {
    return firstLetter;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..city_taobao.first_letter
   *
   * @param firstLetter the value for bim_grab..city_taobao.first_letter
   *
   * @mbggenerated
   */
  public void setFirstLetter(String firstLetter) {
    this.firstLetter = firstLetter == null ? null : firstLetter.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..city_taobao.pinyin
   *
   * @return the value of bim_grab..city_taobao.pinyin
   *
   * @mbggenerated
   */
  public String getPinyin() {
    return pinyin;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..city_taobao.pinyin
   *
   * @param pinyin the value for bim_grab..city_taobao.pinyin
   *
   * @mbggenerated
   */
  public void setPinyin(String pinyin) {
    this.pinyin = pinyin == null ? null : pinyin.trim();
  }
}
