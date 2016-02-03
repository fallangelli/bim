package com.family.webserver.mapper;

import com.family.webserver.entity.CityHotMovie;
import com.family.webserver.entity.Comment;
import com.family.webserver.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMovieMapper {
  List<CityHotMovie> selectCityMovies(@Param("cityId") Integer cityId);

  List<Comment> selectMovieComments(@Param("movieId") Integer movieId);

  List<Image> selectMovieImages(@Param("movieId") Integer movieId);

}
