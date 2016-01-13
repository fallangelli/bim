package com.family.webserver.controller;

import com.family.webserver.entity.CinemaScreening;
import com.family.webserver.entity.CityMovieWithShowDates;
import com.family.webserver.entity.Source;
import com.family.webserver.service.CityScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cityScreening")
public class CityScreeningController {

  @Autowired
  private CityScreeningService service;

  @RequestMapping(value = "/CityMovieWithShowDates", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  CityMovieWithShowDates getCityMovieWithShowDates(@RequestParam(value = "cityId", required = true) Integer cityId,
                                                   @RequestParam(value = "movieId", required = true) Integer movieId,
                                                   @RequestParam(value = "distinctId", required = false) Integer distinctId) {
    CityMovieWithShowDates movieWithShowDates = service.getCityMovieWithShowDates(cityId, movieId, distinctId);
    return movieWithShowDates;
  }

  @RequestMapping(value = "/CinemaScreening", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  CinemaScreening getCinemaScreening(@RequestParam(value = "cinemaId", required = true) Integer cinemaId,
                                     @RequestParam(value = "movieId", required = true) Integer movieId,
                                     @RequestParam(value = "showDate", required = true) Date showDate) {
    CinemaScreening screening = service.getCinemaScreening(cinemaId, movieId, showDate);
    return screening;
  }

  @RequestMapping(value = "/CinemaSource", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<Source> getCinemaSource(@RequestParam(value = "cinemaId", required = true) Integer cinemaId,
                               @RequestParam(value = "movieId", required = true) Integer movieId,
                               @RequestParam(value = "showDate", required = true) Date showDate,
                               @RequestParam(value = "startTime", required = true) Time startTime) {
    List<Source> sourceList = service.getCinemaSource(cinemaId, movieId, showDate, startTime);
    return sourceList;
  }

}
