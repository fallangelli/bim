package com.family.webserver.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityScreeningMapper {


  List<String> selectMovieCinemaShowDatesByCity(@Param("cityId") Integer cityId,
                                                @Param("movieId") Integer movieId);
}
