package com.family.webserver.mapper;

import com.family.webserver.entity.City;

import java.util.List;

public interface CityMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table city
   *
   * @mbggenerated Wed Jan 27 11:44:15 CST 2016
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table city
   *
   * @mbggenerated Wed Jan 27 11:44:15 CST 2016
   */
  int insert(City record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table city
   *
   * @mbggenerated Wed Jan 27 11:44:15 CST 2016
   */
  City selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table city
   *
   * @mbggenerated Wed Jan 27 11:44:15 CST 2016
   */
  List<City> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table city
   *
   * @mbggenerated Wed Jan 27 11:44:15 CST 2016
   */
  int updateByPrimaryKey(City record);
}
