package com.family.grabserver.entity.bim_base;

public class Source {
  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..source.id
   *
   * @mbggenerated
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..source.name
   *
   * @mbggenerated
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator.
   * This field corresponds to the database column bim_base..source.content
   *
   * @mbggenerated
   */
  private String content;

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..source.id
   *
   * @return the value of bim_base..source.id
   * @mbggenerated
   */
  public Integer getId() {
    return id;
  }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bim_base..source.id
     *
     * @param id the value for bim_base..source.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
      this.id = id;
    }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..source.name
   *
   * @return the value of bim_base..source.name
   *
   * @mbggenerated
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..source.name
   *
   * @param name the value for bim_base..source.name
   *
   * @mbggenerated
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method returns the value of the database column bim_base..source.content
   *
   * @return the value of bim_base..source.content
   *
   * @mbggenerated
   */
  public String getContent() {
    return content;
  }

  /**
   * This method was generated by MyBatis Generator.
   * This method sets the value of the database column bim_base..source.content
   *
   * @param content the value for bim_base..source.content
   *
   * @mbggenerated
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }
}
