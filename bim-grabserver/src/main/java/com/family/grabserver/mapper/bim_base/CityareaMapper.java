package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.Cityarea;

import java.util.List;

public interface CityareaMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cityarea
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cityarea
   *
   * @mbggenerated
   */
  int insert(Cityarea record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cityarea
   *
   * @mbggenerated
   */
  Cityarea selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cityarea
   *
   * @mbggenerated
   */
  List<Cityarea> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cityarea
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(Cityarea record);
}
