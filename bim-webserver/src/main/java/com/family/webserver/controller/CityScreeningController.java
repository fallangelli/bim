package com.family.webserver.controller;

import com.family.webserver.service.CityScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cityScreening")
public class CityScreeningController {

  @Autowired
  private CityScreeningService service;

  @RequestMapping(value = "/MovieCinemaShowDates", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<String> getCityMovieCinemaShowDates(@RequestParam(value = "cityId", required = true) Integer cityId,
                                           @RequestParam(value = "movieId", required = true) Integer movieId) {
    List<String> dates = service.getMovieCinemaShowDatesByCity(cityId, movieId);
    return dates;
  }

}
