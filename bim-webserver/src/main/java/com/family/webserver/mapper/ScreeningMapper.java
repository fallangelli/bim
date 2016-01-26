package com.family.webserver.mapper;

import com.family.webserver.entity.Screening;

import java.util.List;

public interface ScreeningMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table screening
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table screening
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  int insert(Screening record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table screening
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  Screening selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table screening
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  List<Screening> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table screening
   *
   * @mbggenerated Tue Jan 26 17:14:54 CST 2016
   */
  int updateByPrimaryKey(Screening record);
}
