package com.family.grabserver.mapper;

import com.family.grabserver.entity.Cityarea;

import java.util.List;

public interface CityareaMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea
   *
   * @mbggenerated
   */
  int insert(Cityarea record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea
   *
   * @mbggenerated
   */
  Cityarea selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea
   *
   * @mbggenerated
   */
  List<Cityarea> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table cityarea
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(Cityarea record);
}
