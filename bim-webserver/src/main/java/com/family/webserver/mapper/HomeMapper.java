package com.family.webserver.mapper;

import com.family.webserver.entity.HomeCityHotMovie;

import java.util.List;

public interface HomeMapper {
  List<HomeCityHotMovie> selectHomeHotMoviesByCity(Integer cityId);
}
