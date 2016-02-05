package com.family.webserver.mapper;

import com.family.webserver.entity.CinemaScreening;
import com.family.webserver.entity.RetMovieWithShowDates;
import com.family.webserver.entity.Source;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CityScreeningMapper {
  RetMovieWithShowDates selectCityMovieWithShowDates(@Param("cityId") Integer cityId,
                                                     @Param("movieId") Integer movieId,
                                                     @Param("districtId") Integer districtId);

  List<String> selectCinemaMovieDates(@Param("cinemaId") Integer cinemaId,
                                      @Param("movieId") Integer movieId);

  CinemaScreening selectCinemaScreening(@Param("cinemaId") Integer cinemaId,
                                        @Param("movieId") Integer movieId,
                                        @Param("showDate") Date showDate);

  List<Source> selectCinemaSource(@Param("cinemaId") Integer cinemaId,
                                  @Param("movieId") Integer movieId,
                                  @Param("showDate") Date showDate,
                                  @Param("startTime") Date startTime);


}
