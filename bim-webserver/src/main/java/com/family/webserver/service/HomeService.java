package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.CityHotMovie;
import com.family.webserver.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class HomeService {
  @Autowired
  private HomeMapper mapper;

  public List<List<CityHotMovie>> getHotMoviesByCity(Integer cityId) {
    List<CityHotMovie> hotMovies = mapper.selectHomeHotMoviesByCity(cityId);

    int midIndex = hotMovies.size() / 2;
    List<CityHotMovie> a = hotMovies.subList(0, midIndex);
    List<CityHotMovie> b = hotMovies.subList(midIndex, hotMovies.size());
    List<List<CityHotMovie>> retVal = new ArrayList<>();
    retVal.add(a);
    retVal.add(b);
    return retVal;
  }

  public Integer getCityIdFromName(String cityName) {
    return mapper.selectCityIdFromName(cityName);
  }

  public List<Cinema> getNearCinemas(String lat, String lng) {
    return mapper.selectNearCinemas(lat, lng);
  }

}
