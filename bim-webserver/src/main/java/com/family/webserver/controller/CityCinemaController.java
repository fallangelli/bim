package com.family.webserver.controller;

import com.family.webserver.entity.ListCinema;
import com.family.webserver.service.CityCinemaService;
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
  private CityCinemaService service;

  @RequestMapping(value = "/Cinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<ListCinema> getCityCinemas(@RequestParam(value = "cityId", required = true) Integer cityId) {
    return service.getCinemaByCity(cityId);
  }


  @RequestMapping(value = "/DateMovieCinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<ListCinema> getCityMovieCinemabyDate(@RequestParam(value = "cityId", required = true) Integer cityId,
                                            @RequestParam(value = "movieId", required = true) Integer movieId,
                                            @RequestParam(value = "showDate", required = true) Date showDate) {
    List<ListCinema> cinemas = service.getMovieCinemaByCity(cityId, movieId, showDate);
    return cinemas;
  }

}
