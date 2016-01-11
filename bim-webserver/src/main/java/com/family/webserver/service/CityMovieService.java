package com.family.webserver.service;

import com.family.webserver.entity.Movieshowing;
import com.family.webserver.mapper.CityCinemaMapper;
import com.family.webserver.mapper.MovieshowingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityMovieService {
  @Autowired
  private MovieshowingMapper cmapper;
  @Autowired
  private CityCinemaMapper ccmapper;

  public Movieshowing getMovieById(Integer movieId) {
    return cmapper.selectByPrimaryKey(movieId);
  }

  public List<Movieshowing> getMoviesByCinemaId(Integer cinemaId) {
    return ccmapper.selectMoviesByCinemaId(cinemaId);
  }
}
