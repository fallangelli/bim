package com.family.grabserver.mapper.bim_grab;

import com.family.grabserver.entity.bim_grab.CinemamovieMaoyan;

import java.util.List;

public interface CinemamovieMaoyanMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cinemamovie_maoyan
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cinemamovie_maoyan
   *
   * @mbggenerated
   */
  int insert(CinemamovieMaoyan record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cinemamovie_maoyan
   *
   * @mbggenerated
   */
  CinemamovieMaoyan selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cinemamovie_maoyan
   *
   * @mbggenerated
   */
  List<CinemamovieMaoyan> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_grab..cinemamovie_maoyan
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(CinemamovieMaoyan record);
}
