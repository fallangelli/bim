package com.family.grabserver.mapper;

import com.family.grabserver.entity.ScreeningMtime;

import java.util.List;

public interface ScreeningMtimeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_mtime
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_mtime
     *
     * @mbggenerated
     */
    int insert(ScreeningMtime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_mtime
     *
     * @mbggenerated
     */
    ScreeningMtime selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_mtime
     *
     * @mbggenerated
     */
    List<ScreeningMtime> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table screening_mtime
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ScreeningMtime record);
}
