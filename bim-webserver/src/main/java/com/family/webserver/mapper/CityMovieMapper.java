package com.family.webserver.mapper;

import com.family.webserver.entity.CityHotMovie;
import com.family.webserver.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMovieMapper {
  List<CityHotMovie> selectCityMovies(@Param("cityId") Integer cityId);

  List<Comment> selectMovieCommets(@Param("movieId") Integer movieId);
}
