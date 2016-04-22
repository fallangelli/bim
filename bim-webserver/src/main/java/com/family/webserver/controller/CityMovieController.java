package com.family.webserver.controller;

import com.family.webserver.entity.RetHotMovie;
import com.family.webserver.entity.RetMovie;
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

  @RequestMapping(value = "/movie", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  RetMovie getMoive(@RequestParam(value = "moiveId", required = true) Integer moiveId) {
    RetMovie retVal = new RetMovie();
    retVal.setMovie(service.getMovieById(moiveId));
    retVal.setComments(service.getMovieComments(moiveId));
    retVal.setImages(service.getMovieImages(moiveId));
    return retVal;
  }

  @RequestMapping(value = "/cityMovies", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<RetHotMovie> getCityMoives(@RequestParam(value = "cityId", required = true) Integer cityId) {
    return service.getCityMoives(cityId);
  }
}
