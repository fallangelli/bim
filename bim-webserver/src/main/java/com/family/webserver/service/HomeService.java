package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.RetCityWithArea;
import com.family.webserver.entity.RetHotMovie;
import com.family.webserver.entity.RetListCity;
import com.family.webserver.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class HomeService {
  @Autowired
  private HomeMapper mapper;

  public List<RetHotMovie> getHotMoviesByCity(Integer cityId) {
    List<RetHotMovie> hotMovies = mapper.selectHomeHotMoviesByCity(cityId);
    return hotMovies;
  }

  public Integer getCityIdFromName(String cityName) {
    return mapper.selectCityIdFromName(cityName);
  }

  public RetCityWithArea getCityInfo(Integer cityId) {
    return mapper.selectCityInfo(cityId);
  }

  public List<Cinema> getNearCinemas(Integer cityId, String lat, String lng) {
    return mapper.selectNearCinemas(cityId, lat, lng);
  }

  public List<RetListCity> getAllCities() {
    return mapper.selectCitiesGroupFL();
  }


}
