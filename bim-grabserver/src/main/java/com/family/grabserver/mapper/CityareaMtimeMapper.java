package com.family.grabserver.mapper;

import com.family.grabserver.entity.CityareaMtime;

import java.util.List;

public interface CityareaMtimeMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea_mtime
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea_mtime
   *
   * @mbggenerated
   */
  int insert(CityareaMtime record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea_mtime
   *
   * @mbggenerated
   */
  CityareaMtime selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea_mtime
   *
   * @mbggenerated
   */
  List<CityareaMtime> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea_mtime
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(CityareaMtime record);
}
