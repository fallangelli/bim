package com.family.grabserver.entity.bim_grab;

public class ImageMtime {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..image_mtime.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..image_mtime.movie_id
   *
   * @mbggenerated
   */
  private Integer movieId;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_grab..image_mtime.image_url
   *
   * @mbggenerated
   */
  private String imageUrl;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..image_mtime.id
   *
   * @return the value of bim_grab..image_mtime.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..image_mtime.id
   *
   * @param id the value for bim_grab..image_mtime.id
   * @mbggenerated
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..image_mtime.movie_id
   *
   * @return the value of bim_grab..image_mtime.movie_id
   * @mbggenerated
   */
  public Integer getMovieId() {
    return movieId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..image_mtime.movie_id
   *
   * @param movieId the value for bim_grab..image_mtime.movie_id
   * @mbggenerated
   */
  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_grab..image_mtime.image_url
   *
   * @return the value of bim_grab..image_mtime.image_url
   * @mbggenerated
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_grab..image_mtime.image_url
   *
   * @param imageUrl the value for bim_grab..image_mtime.image_url
   * @mbggenerated
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl == null ? null : imageUrl.trim();
  }
}
