package com.family.webserver.mapper;

import com.family.webserver.entity.Source;

import java.util.List;

public interface SourceMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table source
   *
   * @mbggenerated Wed Feb 03 15:42:13 CST 2016
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table source
   *
   * @mbggenerated Wed Feb 03 15:42:13 CST 2016
   */
  int insert(Source record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table source
   *
   * @mbggenerated Wed Feb 03 15:42:13 CST 2016
   */
  Source selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table source
   *
   * @mbggenerated Wed Feb 03 15:42:13 CST 2016
   */
  List<Source> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table source
   *
   * @mbggenerated Wed Feb 03 15:42:13 CST 2016
   */
  int updateByPrimaryKey(Source record);
}
