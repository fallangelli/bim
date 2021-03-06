package com.family.webserver.mapper;

import com.family.webserver.entity.Image;

import java.util.List;

public interface ImageMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table image
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table image
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  int insert(Image record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table image
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  Image selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table image
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  List<Image> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table image
   *
   * @mbggenerated Fri Feb 05 11:16:29 CST 2016
   */
  int updateByPrimaryKey(Image record);
}
