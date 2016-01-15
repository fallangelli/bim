package com.family.webserver.controller;

import com.family.webserver.entity.*;
import com.family.webserver.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private HomeService service;

  @RequestMapping(value = "/hotMovies", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<List<CityHotMovie>> getHotMoviesByCity(@RequestParam(value = "cityId", required = true) Integer cityId) {
    List<CityHotMovie> hotMovies = service.getHotMoviesByCity(cityId);
    int midIndex = hotMovies.size() / 2;
    List<CityHotMovie> a = hotMovies.subList(0, midIndex);
    List<CityHotMovie> b = hotMovies.subList(midIndex, hotMovies.size());
    List<List<CityHotMovie>> retVal = new ArrayList<>();
    retVal.add(a);
    retVal.add(b);
    return retVal;
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

  @RequestMapping(value = "/getCityInfo", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  CityWithArea getCityInfo(@RequestParam(value = "cityId", required = true) Integer cityId) {
    return service.getCityInfo(cityId);
  }

  @RequestMapping(value = "/getNearCinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<Cinema> getNearCinemas(@RequestParam(value = "lat", required = true) String lat,
                              @RequestParam(value = "lng", required = true) String lng) {
    return service.getNearCinemas(lat, lng);

  }

  @RequestMapping(value = "/getAllCities", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  Map<String, List<List<City>>> getAllCities() {
    List<ListCity> lists = service.getAllCities();
    Map<String, List<List<City>>> retVal = new HashMap<>();
    for (ListCity list : lists) {
      List<City> cities = list.getCities();
      List<List<City>> groups = new ArrayList<>();
      for (int i = 1; i <= cities.size(); i++) {
        List<City> group = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
          if (i <= cities.size()) {
            group.add(cities.get(i - 1));
            i++;
          }
        }
        groups.add(group);
      }
      retVal.put(list.getFirstLetter(), groups);
    }

    return retVal;

  }
}
