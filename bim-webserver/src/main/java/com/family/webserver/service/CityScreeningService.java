package com.family.webserver.service;

import com.family.webserver.entity.CinemaScreening;
import com.family.webserver.entity.CityMovieWithShowDates;
import com.family.webserver.entity.Source;
import com.family.webserver.mapper.CityScreeningMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityScreeningService {
  @Autowired
  private CityScreeningMapper ccmapper;

  public CityMovieWithShowDates getCityMovieWithShowDates(Integer cityId, Integer movieId, Integer distinctId) {
    return ccmapper.selectCityMovieWithShowDates(cityId, movieId, distinctId);
  }

  public List<String> getCinemaMovieDates(Integer cinemaId, Integer movieId) {
    return ccmapper.selectCinemaMovieDates(cinemaId, movieId);
  }

  public CinemaScreening getCinemaScreening(Integer cinemaId, Integer movieId, Date showDate) {
    return ccmapper.selectCinemaScreening(cinemaId, movieId, showDate);
  }

  public List<Source> getCinemaSource(Integer cinemaId, Integer movieId, Date showDate, Date startTime) {
    return ccmapper.selectCinemaSource(cinemaId, movieId, showDate, startTime);
  }

}
