package com.family.grabserver.mapper.bim_grab;

import com.family.grabserver.entity.bim_grab.CityMtime;

import java.util.List;

public interface CityMtimeMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..city_mtime
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..city_mtime
   *
   * @mbggenerated
   */
  int insert(CityMtime record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..city_mtime
   *
   * @mbggenerated
   */
  CityMtime selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..city_mtime
   *
   * @mbggenerated
   */
  List<CityMtime> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..city_mtime
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(CityMtime record);
}
