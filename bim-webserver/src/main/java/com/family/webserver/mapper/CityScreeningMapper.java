package com.family.webserver.mapper;

import com.family.webserver.entity.CityMovieWithShowDates;
import com.family.webserver.entity.CityScreening;
import com.family.webserver.entity.Source;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CityScreeningMapper {
  CityMovieWithShowDates selectCityMovieWithShowDates(@Param("cityId") Integer cityId,
                                                      @Param("movieId") Integer movieId);

  List<String> selectCinemaMovieDates(@Param("cinemaId") Integer cinemaId,
                                      @Param("movieId") Integer movieId);

  CityScreening selectCinemaScreening(@Param("cinemaId") Integer cinemaId,
                                      @Param("movieId") Integer movieId,
                                      @Param("showDate") Date showDate);

  List<Source> selectCinemaSource(@Param("cinemaId") Integer cinemaId,
                                  @Param("movieId") Integer movieId,
                                  @Param("showDate") Date showDate,
                                  @Param("startTime") Date startTime);


}
