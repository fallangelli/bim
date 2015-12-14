package com.family.webserver.mapper;

import com.family.webserver.entity.HomeCityHotMovie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeMapper {
  List<HomeCityHotMovie> selectHomeHotMoviesByCity(Integer cityId);

  Integer selectCityIdFromName(@Param("cityName") String cityName);
}
