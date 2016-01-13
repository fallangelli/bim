package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.ListCinema;
import com.family.webserver.mapper.CinemaMapper;
import com.family.webserver.mapper.CityCinemaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityCinemaService {
  @Autowired
  private CityCinemaMapper ccmapper;
  @Autowired
  private CinemaMapper cmapper;

  public Cinema getCinemaById(Integer cityId) {
    return cmapper.selectByPrimaryKey(cityId);
  }

  public List<ListCinema> getCityCinemas(Integer cityId, String lat, String lng, String orderBy, Integer distinctId, String nameLike) {
    return ccmapper.selectCityCinemas(cityId, lat, lng, orderBy, distinctId, nameLike);
  }

  public List<ListCinema> getMovieCinemaByCity(Integer cityId, Integer movieId, Date showDate, String lat, String lng, String orderBy, Integer distinctId) {
    return ccmapper.selectMovieCinemaByCity(cityId, movieId, showDate, lat, lng, orderBy, distinctId);
  }
}
