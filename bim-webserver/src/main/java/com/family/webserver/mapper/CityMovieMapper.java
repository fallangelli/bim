package com.family.webserver.mapper;

import com.family.webserver.entity.Comment;
import com.family.webserver.entity.Image;
import com.family.webserver.entity.RetHotMovie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMovieMapper {
  List<RetHotMovie> selectCityMovies(@Param("cityId") Integer cityId);

  List<Comment> selectMovieComments(@Param("movieId") Integer movieId);

  List<Image> selectMovieImages(@Param("movieId") Integer movieId);

}
