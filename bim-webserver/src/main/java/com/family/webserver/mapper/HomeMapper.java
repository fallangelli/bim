package com.family.webserver.mapper;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.CityHotMovie;
import com.family.webserver.entity.CityWithArea;
import com.family.webserver.entity.ListCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeMapper {
  List<CityHotMovie> selectHomeHotMoviesByCity(Integer cityId);

  Integer selectCityIdFromName(@Param("cityName") String cityName);

  CityWithArea selectCityInfo(@Param("cityId") Integer cityId);

  List<Cinema> selectNearCinemas(@Param("lat") String lat, @Param("lng") String lng);

  List<ListCity> selectCitiesGroupFL();
}
