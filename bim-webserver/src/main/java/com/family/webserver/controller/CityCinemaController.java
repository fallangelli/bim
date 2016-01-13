package com.family.webserver.controller;

import com.family.webserver.entity.CinemaScreening;
import com.family.webserver.entity.CityCinema;
import com.family.webserver.entity.ListCinema;
import com.family.webserver.service.CityCinemaService;
import com.family.webserver.service.CityMovieService;
import com.family.webserver.service.CityScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cityCinemas")
public class CityCinemaController {

  @Autowired
  private CityCinemaService cservice;

  @Autowired
  private CityMovieService mService;

  @Autowired
  private CityScreeningService sService;

  @RequestMapping(value = "/Cinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<ListCinema> getCityCinemas(@RequestParam(value = "cityId", required = true) Integer cityId,
                                  @RequestParam(value = "lat", required = false) String lat,
                                  @RequestParam(value = "lng", required = false) String lng,
                                  @RequestParam(value = "orderBy", required = false) String orderBy,
                                  @RequestParam(value = "distinctId", required = false) Integer distinctId,
                                  @RequestParam(value = "nameLike", required = false) String nameLike) {

    List<ListCinema> retList = cservice.getCityCinemas(cityId, lat, lng, orderBy, distinctId, nameLike);
    return retList;
  }

  @RequestMapping(value = "/DateMovieCinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<ListCinema> getCityMovieCinemabyDate(@RequestParam(value = "cityId", required = true) Integer cityId,
                                            @RequestParam(value = "movieId", required = true) Integer movieId,
                                            @RequestParam(value = "showDate", required = true) Date showDate,
                                            @RequestParam(value = "lat", required = true) String lat,
                                            @RequestParam(value = "lng", required = true) String lng,
                                            @RequestParam(value = "orderBy", required = false) String orderBy,
                                            @RequestParam(value = "distinctId", required = false) Integer distinctId) {
    List<ListCinema> cinemas = cservice.getMovieCinemaByCity(cityId, movieId, showDate, lat, lng, orderBy, distinctId);
    return cinemas;
  }

  @RequestMapping(value = "/Cinema", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  CityCinema getCinemaWithMovies(@RequestParam(value = "cinemaId", required = true) Integer cinemaId) {
    CityCinema cc = new CityCinema();
    cc.setCinema(cservice.getCinemaById(cinemaId));
    cc.setShowingMovies(mService.getMoviesByCinemaId(cinemaId));
    return cc;
  }

  @RequestMapping(value = "/CinemaMovieDates", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<String> getCinemaMovieDates(@RequestParam(value = "cinemaId", required = true) Integer cinemaId,
                                   @RequestParam(value = "movieId", required = true) Integer movieId) {
    List<String> dates = sService.getCinemaMovieDates(cinemaId, movieId);
    return dates;
  }

  @RequestMapping(value = "/CinemaMovieSourcesByDate", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  CinemaScreening getCinemaMovieSourcesByDate(@RequestParam(value = "cinemaId", required = true) Integer cinemaId,
                                              @RequestParam(value = "movieId", required = true) Integer movieId,
                                              @RequestParam(value = "date", required = true) Date date) {
    CinemaScreening screening = sService.getCinemaScreening(cinemaId, movieId, date);
    return screening;
  }


}
