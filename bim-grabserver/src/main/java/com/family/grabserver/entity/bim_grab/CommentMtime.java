package com.family.grabserver.entity.bim_grab;

public class CommentMtime {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..comment_mtime.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..comment_mtime.movie_id
   *
   * @mbggenerated
   */
  private Integer movieId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..comment_mtime.title
   *
   * @mbggenerated
   */
  private String title;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..comment_mtime.nick_name
   *
   * @mbggenerated
   */
  private String nickName;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..comment_mtime.rating
   *
   * @mbggenerated
   */
  private Float rating;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..comment_mtime.content
   *
   * @mbggenerated
   */
  private String content;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..comment_mtime.id
   *
   * @return the value of bim_grab..comment_mtime.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..comment_mtime.id
   *
   * @param id the value for bim_grab..comment_mtime.id
   * @mbggenerated
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..comment_mtime.movie_id
   *
   * @return the value of bim_grab..comment_mtime.movie_id
   * @mbggenerated
   */
  public Integer getMovieId() {
    return movieId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..comment_mtime.movie_id
   *
   * @param movieId the value for bim_grab..comment_mtime.movie_id
   * @mbggenerated
   */
  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..comment_mtime.title
   *
   * @return the value of bim_grab..comment_mtime.title
   * @mbggenerated
   */
  public String getTitle() {
    return title;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..comment_mtime.title
   *
   * @param title the value for bim_grab..comment_mtime.title
   * @mbggenerated
   */
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..comment_mtime.nick_name
   *
   * @return the value of bim_grab..comment_mtime.nick_name
   * @mbggenerated
   */
  public String getNickName() {
    return nickName;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..comment_mtime.nick_name
   *
   * @param nickName the value for bim_grab..comment_mtime.nick_name
   * @mbggenerated
   */
  public void setNickName(String nickName) {
    this.nickName = nickName == null ? null : nickName.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..comment_mtime.rating
   *
   * @return the value of bim_grab..comment_mtime.rating
   * @mbggenerated
   */
  public Float getRating() {
    return rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..comment_mtime.rating
   *
   * @param rating the value for bim_grab..comment_mtime.rating
   * @mbggenerated
   */
  public void setRating(Float rating) {
    this.rating = rating;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..comment_mtime.content
   *
   * @return the value of bim_grab..comment_mtime.content
   * @mbggenerated
   */
  public String getContent() {
    return content;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..comment_mtime.content
   *
   * @param content the value for bim_grab..comment_mtime.content
   * @mbggenerated
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }
}
