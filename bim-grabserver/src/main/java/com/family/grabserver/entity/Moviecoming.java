package com.family.grabserver.entity;

public class Moviecoming {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.title
   *
   * @mbggenerated
   */
  private String title;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.director
   *
   * @mbggenerated
   */
  private String director;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.actor
   *
   * @mbggenerated
   */
  private String actor;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.type
   *
   * @mbggenerated
   */
  private String type;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.source
   *
   * @mbggenerated
   */
  private String source;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.url
   *
   * @mbggenerated
   */
  private String url;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column moviecoming.urlMd5
   *
   * @mbggenerated
   */
  private String urlmd5;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.id
   *
   * @return the value of moviecoming.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column moviecoming.id
     *
     * @param id the value for moviecoming.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.title
   *
   * @return the value of moviecoming.title
   *
   * @mbggenerated
   */
  public String getTitle() {
    return title;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column moviecoming.title
   *
   * @param title the value for moviecoming.title
   *
   * @mbggenerated
   */
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.director
   *
   * @return the value of moviecoming.director
   *
   * @mbggenerated
   */
  public String getDirector() {
    return director;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column moviecoming.director
   *
   * @param director the value for moviecoming.director
   *
   * @mbggenerated
   */
  public void setDirector(String director) {
    this.director = director == null ? null : director.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.actor
   *
   * @return the value of moviecoming.actor
   *
   * @mbggenerated
   */
  public String getActor() {
    return actor;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column moviecoming.actor
   *
   * @param actor the value for moviecoming.actor
   *
   * @mbggenerated
   */
  public void setActor(String actor) {
    this.actor = actor == null ? null : actor.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.type
   *
   * @return the value of moviecoming.type
   *
   * @mbggenerated
   */
  public String getType() {
    return type;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column moviecoming.type
   *
   * @param type the value for moviecoming.type
   *
   * @mbggenerated
   */
  public void setType(String type) {
    this.type = type == null ? null : type.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.source
   *
   * @return the value of moviecoming.source
   *
   * @mbggenerated
   */
  public String getSource() {
    return source;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column moviecoming.source
   *
   * @param source the value for moviecoming.source
   *
   * @mbggenerated
   */
  public void setSource(String source) {
    this.source = source == null ? null : source.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.url
   *
   * @return the value of moviecoming.url
   *
   * @mbggenerated
   */
  public String getUrl() {
    return url;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column moviecoming.url
   *
   * @param url the value for moviecoming.url
   *
   * @mbggenerated
   */
  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column moviecoming.urlMd5
   *
   * @return the value of moviecoming.urlMd5
   *
   * @mbggenerated
   */
  public String getUrlmd5() {
    return urlmd5;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column moviecoming.urlMd5
   *
   * @param urlmd5 the value for moviecoming.urlMd5
   *
   * @mbggenerated
   */
  public void setUrlmd5(String urlmd5) {
    this.urlmd5 = urlmd5 == null ? null : urlmd5.trim();
  }
}
