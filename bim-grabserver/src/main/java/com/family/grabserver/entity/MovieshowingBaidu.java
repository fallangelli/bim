package com.family.grabserver.entity;

public class MovieshowingBaidu {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing_baidu.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing_baidu.name
   *
   * @mbggenerated
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing_baidu.image
   *
   * @mbggenerated
   */
  private String image;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing_baidu.rating
   *
   * @mbggenerated
   */
  private String rating;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing_baidu.content
   *
   * @mbggenerated
   */
  private String content;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing_baidu.id
   *
   * @return the value of movieshowing_baidu.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing_baidu.id
   *
   * @param id the value for movieshowing_baidu.id
   * @mbggenerated
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing_baidu.name
   *
   * @return the value of movieshowing_baidu.name
   * @mbggenerated
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing_baidu.name
   *
   * @param name the value for movieshowing_baidu.name
   * @mbggenerated
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing_baidu.image
   *
   * @return the value of movieshowing_baidu.image
   * @mbggenerated
   */
  public String getImage() {
    return image;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing_baidu.image
   *
   * @param image the value for movieshowing_baidu.image
   * @mbggenerated
   */
  public void setImage(String image) {
    this.image = image == null ? null : image.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing_baidu.rating
   *
   * @return the value of movieshowing_baidu.rating
   * @mbggenerated
   */
  public String getRating() {
    return rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing_baidu.rating
   *
   * @param rating the value for movieshowing_baidu.rating
   * @mbggenerated
   */
  public void setRating(String rating) {
    this.rating = rating == null ? null : rating.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing_baidu.content
   *
   * @return the value of movieshowing_baidu.content
   * @mbggenerated
   */
  public String getContent() {
    return content;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing_baidu.content
   *
   * @param content the value for movieshowing_baidu.content
   * @mbggenerated
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }
}
