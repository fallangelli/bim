package com.family.grabserver.mapper.bim_grab;

import com.family.grabserver.entity.bim_grab.CityareaBaidu;

import java.util.List;

public interface CityareaBaiduMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cityarea_baidu
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cityarea_baidu
   *
   * @mbggenerated
   */
  int insert(CityareaBaidu record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cityarea_baidu
   *
   * @mbggenerated
   */
  CityareaBaidu selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cityarea_baidu
   *
   * @mbggenerated
   */
  List<CityareaBaidu> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cityarea_baidu
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(CityareaBaidu record);
}