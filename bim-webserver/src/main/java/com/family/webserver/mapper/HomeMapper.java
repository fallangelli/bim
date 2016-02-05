package com.family.webserver.mapper;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.RetCityWithArea;
import com.family.webserver.entity.RetHotMovie;
import com.family.webserver.entity.RetListCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeMapper {
  List<RetHotMovie> selectHomeHotMoviesByCity(Integer cityId);

  Integer selectCityIdFromName(@Param("cityName") String cityName);

  RetCityWithArea selectCityInfo(@Param("cityId") Integer cityId);

  List<Cinema> selectNearCinemas(@Param("cityId") Integer cityId, @Param("lat") String lat, @Param("lng") String lng);

  List<RetListCity> selectCitiesGroupFL();
}
