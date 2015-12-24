package com.family.webserver.controller;

import com.family.webserver.entity.HomeCityHotMovie;
import com.family.webserver.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private HomeService service;

  @RequestMapping(value = "/hotMovies", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<List<HomeCityHotMovie>> getHotMoviesByCity(@RequestParam(value = "cityId", required = true) Integer cityId) {
    return service.getHotMoviesByCity(cityId);
  }

  @RequestMapping(value = "/getCityIdFromName", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  Integer getCityIdFromName(@RequestParam(value = "cityName", required = true) String cityName) {
    if (cityName.length() > 1) {
      return service.getCityIdFromName(cityName);
    }
    return null;
  }
}
