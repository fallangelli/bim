package com.family.grabserver.mapper;

import com.family.grabserver.entity.ScreeningBaidu;

import java.util.List;

public interface ScreeningBaiduMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_baidu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_baidu
     *
     * @mbggenerated
     */
    int insert(ScreeningBaidu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_baidu
     *
     * @mbggenerated
     */
    ScreeningBaidu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_baidu
     *
     * @mbggenerated
     */
    List<ScreeningBaidu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_baidu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ScreeningBaidu record);
}
