package com.family.grabserver.mapper;

import com.family.grabserver.entity.CityareaBaidu;

import java.util.List;

public interface CityareaBaiduMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea_baidu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea_baidu
     *
     * @mbggenerated
     */
    int insert(CityareaBaidu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea_baidu
     *
     * @mbggenerated
     */
    CityareaBaidu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea_baidu
     *
     * @mbggenerated
     */
    List<CityareaBaidu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea_baidu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CityareaBaidu record);
}
