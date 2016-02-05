package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.Source;

import java.util.List;

public interface SourceMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..source
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..source
   *
   * @mbggenerated
   */
  int insert(Source record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..source
   *
   * @mbggenerated
   */
  Source selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..source
   *
   * @mbggenerated
   */
  List<Source> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..source
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(Source record);
}