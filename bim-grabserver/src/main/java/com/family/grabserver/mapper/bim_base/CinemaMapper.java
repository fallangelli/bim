package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.Cinema;

import java.util.List;

public interface CinemaMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cinema
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cinema
   *
   * @mbggenerated
   */
  int insert(Cinema record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cinema
   *
   * @mbggenerated
   */
  Cinema selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cinema
   *
   * @mbggenerated
   */
  List<Cinema> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..cinema
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(Cinema record);
}
