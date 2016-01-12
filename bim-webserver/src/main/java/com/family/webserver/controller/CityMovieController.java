package com.family.webserver.controller;

import com.family.webserver.entity.CityHotMovie;
import com.family.webserver.entity.Movieshowing;
import com.family.webserver.service.CityMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cityMovies")
public class CityMovieController {

  @Autowired
  private CityMovieService service;

  @RequestMapping(value = "/Movie", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  Movieshowing getMoive(@RequestParam(value = "moiveId", required = true) Integer moiveId) {
    return service.getMovieById(moiveId);
  }

  @RequestMapping(value = "/CityMovies", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<CityHotMovie> getCityMoives(@RequestParam(value = "cityId", required = true) Integer cityId) {
    return service.getCityMoives(cityId);
  }
}
