package com.family.webserver.service;

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
    List<HomeCityHotMovie> a = hotMovies.subList(0, 3);
    List<HomeCityHotMovie> b = hotMovies.subList(3, 6);
    List<List<HomeCityHotMovie>> retVal = new ArrayList<>();
    retVal.add(a);
    retVal.add(b);
    return retVal;
  }
}
