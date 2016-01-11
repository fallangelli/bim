package com.family.webserver.controller;

import com.family.webserver.entity.CityCinema;
import com.family.webserver.entity.ListCinema;
import com.family.webserver.service.CityCinemaService;
import com.family.webserver.service.CityMovieService;
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

  @RequestMapping(value = "/Cinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<ListCinema> getCityCinemas(@RequestParam(value = "cityId", required = true) Integer cityId) {
    return cservice.getCinemaByCity(cityId);
  }

  @RequestMapping(value = "/DateMovieCinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<ListCinema> getCityMovieCinemabyDate(@RequestParam(value = "cityId", required = true) Integer cityId,
                                            @RequestParam(value = "movieId", required = true) Integer movieId,
                                            @RequestParam(value = "showDate", required = true) Date showDate) {
    List<ListCinema> cinemas = cservice.getMovieCinemaByCity(cityId, movieId, showDate);
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


}
