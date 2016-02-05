package com.family.webserver.service;

import com.family.webserver.entity.Comment;
import com.family.webserver.entity.Image;
import com.family.webserver.entity.Movieshowing;
import com.family.webserver.entity.RetHotMovie;
import com.family.webserver.mapper.CityCinemaMapper;
import com.family.webserver.mapper.CityMovieMapper;
import com.family.webserver.mapper.MovieshowingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityMovieService {
  @Autowired
  private MovieshowingMapper mmapper;
  @Autowired
  private CityCinemaMapper ccmapper;
  @Autowired
  private CityMovieMapper cmmapper;

  public Movieshowing getMovieById(Integer movieId) {
    return mmapper.selectByPrimaryKey(movieId);
  }

  public List<RetHotMovie> getCityMoives(Integer cityId) {
    return cmmapper.selectCityMovies(cityId);
  }

  public List<Movieshowing> getShowingMoviesByCinemaId(Integer cinemaId) {
    return ccmapper.selectShowingMoviesByCinemaId(cinemaId);
  }


  public List<Comment> getMovieComments(Integer movieId) {
    return cmmapper.selectMovieComments(movieId);
  }


  public List<Image> getMovieImages(Integer movieId) {
    return cmmapper.selectMovieImages(movieId);
  }
}
