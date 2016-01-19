package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.CityArea;

import java.util.List;

public interface CityAreaMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..city_area
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..city_area
   *
   * @mbggenerated
   */
  int insert(CityArea record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..city_area
   *
   * @mbggenerated
   */
  CityArea selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..city_area
   *
   * @mbggenerated
   */
  List<CityArea> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..city_area
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(CityArea record);
}
