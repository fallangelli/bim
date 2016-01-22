package com.family.webserver.entity;

public class Movieshowing {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.id
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.name
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.image
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private String image;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.rating
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private Double rating;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.is_imax
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private Boolean isImax;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.is_3d
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private Boolean is3d;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.directors
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private String directors;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.actors
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private String actors;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.type
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private String type;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.content
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private String content;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.runTime
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private String runtime;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.id_maoyan
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private Integer idMaoyan;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.id_mtime
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private Integer idMtime;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column movieshowing.id_baidu
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  private Integer idBaidu;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movieshowing.id
     *
     * @return the value of movieshowing.id
     *
     * @mbggenerated Fri Jan 22 17:09:15 CST 2016
     */
    public Integer getId() {
      return id;
    }

  /**
   * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movieshowing.id
     *
     * @param id the value for movieshowing.id
     *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.name
   *
   * @return the value of movieshowing.name
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public String getName() {
    return name;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movieshowing.name
     *
     * @param name the value for movieshowing.name
     *
     * @mbggenerated Fri Jan 22 17:09:15 CST 2016
     */
    public void setName(String name) {
      this.name = name == null ? null : name.trim();
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.image
   *
   * @return the value of movieshowing.image
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public String getImage() {
    return image;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.image
   *
   * @param image the value for movieshowing.image
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setImage(String image) {
    this.image = image == null ? null : image.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.rating
   *
   * @return the value of movieshowing.rating
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public Double getRating() {
    return rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.rating
   *
   * @param rating the value for movieshowing.rating
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setRating(Double rating) {
    this.rating = rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.is_imax
   *
   * @return the value of movieshowing.is_imax
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public Boolean getIsImax() {
    return isImax;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.is_imax
   *
   * @param isImax the value for movieshowing.is_imax
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setIsImax(Boolean isImax) {
    this.isImax = isImax;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.is_3d
   *
   * @return the value of movieshowing.is_3d
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public Boolean getIs3d() {
    return is3d;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.is_3d
   *
   * @param is3d the value for movieshowing.is_3d
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setIs3d(Boolean is3d) {
    this.is3d = is3d;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.directors
   *
   * @return the value of movieshowing.directors
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public String getDirectors() {
    return directors;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.directors
   *
   * @param directors the value for movieshowing.directors
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setDirectors(String directors) {
    this.directors = directors == null ? null : directors.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.actors
   *
   * @return the value of movieshowing.actors
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public String getActors() {
    return actors;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.actors
   *
   * @param actors the value for movieshowing.actors
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setActors(String actors) {
    this.actors = actors == null ? null : actors.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.type
   *
   * @return the value of movieshowing.type
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public String getType() {
    return type;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.type
   *
   * @param type the value for movieshowing.type
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setType(String type) {
    this.type = type == null ? null : type.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.content
   *
   * @return the value of movieshowing.content
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public String getContent() {
    return content;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.content
   *
   * @param content the value for movieshowing.content
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.runTime
   *
   * @return the value of movieshowing.runTime
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public String getRuntime() {
    return runtime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.runTime
   *
   * @param runtime the value for movieshowing.runTime
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setRuntime(String runtime) {
    this.runtime = runtime == null ? null : runtime.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.id_maoyan
   *
   * @return the value of movieshowing.id_maoyan
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public Integer getIdMaoyan() {
    return idMaoyan;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.id_maoyan
   *
   * @param idMaoyan the value for movieshowing.id_maoyan
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setIdMaoyan(Integer idMaoyan) {
    this.idMaoyan = idMaoyan;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.id_mtime
   *
   * @return the value of movieshowing.id_mtime
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public Integer getIdMtime() {
    return idMtime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.id_mtime
   *
   * @param idMtime the value for movieshowing.id_mtime
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setIdMtime(Integer idMtime) {
    this.idMtime = idMtime;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column movieshowing.id_baidu
   *
   * @return the value of movieshowing.id_baidu
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public Integer getIdBaidu() {
    return idBaidu;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column movieshowing.id_baidu
   *
   * @param idBaidu the value for movieshowing.id_baidu
   *
   * @mbggenerated Fri Jan 22 17:09:15 CST 2016
   */
  public void setIdBaidu(Integer idBaidu) {
    this.idBaidu = idBaidu;
  }
}
