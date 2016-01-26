package com.family.webserver.mapper;

import com.family.webserver.entity.Cinema;

import java.util.List;

public interface CinemaMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cinema
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cinema
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  int insert(Cinema record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cinema
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  Cinema selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cinema
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  List<Cinema> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cinema
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  int updateByPrimaryKey(Cinema record);
}
