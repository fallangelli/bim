package com.family.webserver.entity;

public class City {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column city.id
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column city.name
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column city.first_letter
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  private String firstLetter;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column city.pinyin
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  private String pinyin;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.id
     *
     * @return the value of city.id
     *
     * @mbggenerated Tue Jan 26 17:14:54 CST 2016
     */
    public Integer getId() {
      return id;
    }

  /**
   * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.id
     *
     * @param id the value for city.id
     *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column city.name
   *
   * @return the value of city.name
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public String getName() {
    return name;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.name
     *
     * @param name the value for city.name
     *
     * @mbggenerated Tue Jan 26 17:14:54 CST 2016
     */
    public void setName(String name) {
      this.name = name == null ? null : name.trim();
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column city.first_letter
   *
   * @return the value of city.first_letter
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public String getFirstLetter() {
    return firstLetter;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column city.first_letter
   *
   * @param firstLetter the value for city.first_letter
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public void setFirstLetter(String firstLetter) {
    this.firstLetter = firstLetter == null ? null : firstLetter.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column city.pinyin
   *
   * @return the value of city.pinyin
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public String getPinyin() {
    return pinyin;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column city.pinyin
   *
   * @param pinyin the value for city.pinyin
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  public void setPinyin(String pinyin) {
    this.pinyin = pinyin == null ? null : pinyin.trim();
  }
}
