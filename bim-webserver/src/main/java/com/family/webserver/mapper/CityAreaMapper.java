package com.family.webserver.mapper;

import com.family.webserver.entity.Cityarea;

import java.util.List;

public interface CityareaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea
     *
     * @mbggenerated Tue Feb 02 16:34:46 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea
     *
     * @mbggenerated Tue Feb 02 16:34:46 CST 2016
     */
    int insert(Cityarea record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea
     *
     * @mbggenerated Tue Feb 02 16:34:46 CST 2016
     */
    Cityarea selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea
     *
     * @mbggenerated Tue Feb 02 16:34:46 CST 2016
     */
    List<Cityarea> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cityarea
     *
     * @mbggenerated Tue Feb 02 16:34:46 CST 2016
     */
    int updateByPrimaryKey(Cityarea record);
}
