package com.family.webserver.service;

import com.family.webserver.entity.CityMovieWithShowDates;
import com.family.webserver.entity.CityScreening;
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

  public CityMovieWithShowDates getCityMovieWithShowDates(Integer cityId, Integer movieId) {
    return ccmapper.selectCityMovieWithShowDates(cityId, movieId);
  }

  public CityScreening getCityScreening(Integer cityId, Integer cinemaId, Integer movieId, Date showDate) {
    return ccmapper.selectCityScreening(cityId, cinemaId, movieId, showDate);
  }

  public List<Source> getCitySource(Integer cityId, Integer cinemaId, Integer movieId, Date showDate, Date startTime) {
    return ccmapper.selectCitySource(cityId, cinemaId, movieId, showDate, startTime);
  }

}
