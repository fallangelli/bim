package com.family.grabserver.mapper;

import com.family.grabserver.entity.MovieshowingMtime;

import java.util.List;

public interface MovieshowingMtimeMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing_mtime
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing_mtime
   *
   * @mbggenerated
   */
  int insert(MovieshowingMtime record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing_mtime
   *
   * @mbggenerated
   */
  MovieshowingMtime selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing_mtime
   *
   * @mbggenerated
   */
  List<MovieshowingMtime> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing_mtime
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(MovieshowingMtime record);
}
