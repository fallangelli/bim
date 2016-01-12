package com.family.webserver.mapper;

import com.family.webserver.entity.ListCinema;
import com.family.webserver.entity.Movieshowing;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CityCinemaMapper {

  List<ListCinema> selectByCity(@Param("cityId") Integer cityId,
                                @Param("lat") String lat,
                                @Param("lng") String lng);

  List<ListCinema> selectMovieCinemaByCity(@Param("cityId") Integer cityId,
                                           @Param("movieId") Integer movieId,
                                           @Param("showDate") Date showDate);

  List<Movieshowing> selectMoviesByCinemaId(@Param("cinemaId") Integer cinemaId);
}
