package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.CityHotMovie;
import com.family.webserver.entity.CityWithArea;
import com.family.webserver.entity.ListCity;
import com.family.webserver.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class HomeService {
  @Autowired
  private HomeMapper mapper;

  public List<CityHotMovie> getHotMoviesByCity(Integer cityId) {
    List<CityHotMovie> hotMovies = mapper.selectHomeHotMoviesByCity(cityId);

    return hotMovies;
  }


  public Integer getCityIdFromName(String cityName) {
    return mapper.selectCityIdFromName(cityName);
  }

  public CityWithArea getCityInfo(Integer cityId) {
    return mapper.selectCityInfo(cityId);
  }

  public List<Cinema> getNearCinemas(String lat, String lng) {
    return mapper.selectNearCinemas(lat, lng);
  }

  public List<ListCity> getAllCities() {
    return mapper.selectCitiesGroupFL();
  }


}
