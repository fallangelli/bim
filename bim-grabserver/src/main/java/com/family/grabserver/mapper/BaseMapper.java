package com.family.grabserver.mapper;

import com.family.grabserver.entity.Base;

import java.util.List;

public interface BaseMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table base
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table base
   *
   * @mbggenerated
   */
  int insert(Base record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table base
   *
   * @mbggenerated
   */
  List<Base> selectAll();
}
