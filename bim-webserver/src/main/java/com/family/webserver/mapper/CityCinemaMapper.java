package com.family.webserver.mapper;

import com.family.webserver.entity.MovieCinema;
import com.family.webserver.entity.Movieshowing;
import com.family.webserver.entity.RetListCinema;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CityCinemaMapper {
  List<RetListCinema> selectCityCinemas(@Param("cityId") Integer cityId,
                                        @Param("lat") String lat,
                                        @Param("lng") String lng,
                                        @Param("orderBy") String orderBy,
                                        @Param("districtId") Integer districtId,
                                        @Param("nameLike") String nameLike,
                                        @Param("startIndex") Integer startIndex,
                                        @Param("pageCount") Integer pageCount);


  List<MovieCinema> selectMovieCinemaByDate(@Param("cityId") Integer cityId,
                                            @Param("movieId") Integer movieId,
                                            @Param("showDate") Date showDate,
                                            @Param("lat") String lat,
                                            @Param("lng") String lng);

  List<Movieshowing> selectShowingMoviesByCinemaId(@Param("cinemaId") Integer cinemaId);
}
