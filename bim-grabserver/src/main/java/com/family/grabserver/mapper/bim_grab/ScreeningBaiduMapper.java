package com.family.grabserver.mapper.bim_grab;

import com.family.grabserver.entity.bim_grab.ScreeningBaidu;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ScreeningBaiduMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..screening_baidu
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(@Param("cinemaId") Integer cinemaId, @Param("movieId") Integer movieId, @Param("showDate") Date showDate, @Param("startTime") Date startTime, @Param("hall") String hall);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..screening_baidu
   *
   * @mbggenerated
   */
  int insert(ScreeningBaidu record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..screening_baidu
   *
   * @mbggenerated
   */
  ScreeningBaidu selectByPrimaryKey(@Param("cinemaId") Integer cinemaId, @Param("movieId") Integer movieId, @Param("showDate") Date showDate, @Param("startTime") Date startTime, @Param("hall") String hall);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..screening_baidu
   *
   * @mbggenerated
   */
  List<ScreeningBaidu> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..screening_baidu
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(ScreeningBaidu record);
}
