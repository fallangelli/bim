package com.family.webserver.service;

import com.family.webserver.mapper.CityScreeningMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityScreeningService {
  @Autowired
  private CityScreeningMapper ccmapper;

  public List<String> getMovieCinemaShowDatesByCity(Integer cityId, Integer movieId) {
    return ccmapper.selectMovieCinemaShowDatesByCity(cityId, movieId);
  }
}
