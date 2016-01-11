package com.family.webserver.controller;

import com.family.webserver.entity.Movieshowing;
import com.family.webserver.service.CityMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cityMovies")
public class CityMovieController {

  @Autowired
  private CityMovieService service;

  @RequestMapping(value = "/Movie", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  Movieshowing getCityMoive(@RequestParam(value = "moiveId", required = true) Integer moiveId) {
    return service.getMovieById(moiveId);
  }


}
