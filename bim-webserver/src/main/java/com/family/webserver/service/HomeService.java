package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.HomeCityHotMovie;
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

  public List<List<HomeCityHotMovie>> getHotMoviesByCity(Integer cityId) {
    List<HomeCityHotMovie> hotMovies = mapper.selectHomeHotMoviesByCity(cityId);

    int midIndex = hotMovies.size() / 2;
    List<HomeCityHotMovie> a = hotMovies.subList(0, midIndex);
    List<HomeCityHotMovie> b = hotMovies.subList(midIndex, hotMovies.size());
    List<List<HomeCityHotMovie>> retVal = new ArrayList<>();
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
