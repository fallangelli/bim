package com.family.webserver.mapper;

import com.family.webserver.entity.Movieshowing;

import java.util.List;

public interface MovieshowingMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing
   *
   * @mbggenerated Tue Jan 12 17:06:25 CST 2016
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing
   *
   * @mbggenerated Tue Jan 12 17:06:25 CST 2016
   */
  int insert(Movieshowing record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing
   *
   * @mbggenerated Tue Jan 12 17:06:25 CST 2016
   */
  Movieshowing selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing
   *
   * @mbggenerated Tue Jan 12 17:06:25 CST 2016
   */
  List<Movieshowing> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table movieshowing
   *
   * @mbggenerated Tue Jan 12 17:06:25 CST 2016
   */
  int updateByPrimaryKey(Movieshowing record);
}
