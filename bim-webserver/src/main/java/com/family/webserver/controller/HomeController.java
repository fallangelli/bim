package com.family.webserver.controller;

import com.family.webserver.entity.*;
import com.family.webserver.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
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
  List<List<RetHotMovie>> getHotMoviesByCity(@RequestParam(value = "cityId", required = true) Integer cityId) {
    List<RetHotMovie> hotMovies = service.getHotMoviesByCity(cityId);
    int midIndex = hotMovies.size() / 2;
    List<RetHotMovie> a = hotMovies.subList(0, midIndex);
    List<RetHotMovie> b = hotMovies.subList(midIndex, hotMovies.size());
    List<List<RetHotMovie>> retVal = new ArrayList<>();
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
  RetCityWithArea getCityInfo(@RequestParam(value = "cityId", required = true) Integer cityId) {
    return service.getCityInfo(cityId);
  }

  @RequestMapping(value = "/getNearCinemas", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  List<Cinema> getNearCinemas(@RequestParam(value = "cityId", required = true) Integer cityId,
                              @RequestParam(value = "lat", required = false) String lat,
                              @RequestParam(value = "lng", required = false) String lng) {
    return service.getNearCinemas(cityId, lat, lng);

  }

  @RequestMapping(value = "/getAllCities", method = RequestMethod.GET, produces = "application/json")
  public
  @ResponseBody
  Map<String, List<List<City>>> getAllCities() {
    List<RetListCity> lists = service.getAllCities();
    Map<String, List<List<City>>> retVal = new HashMap<>();
    for (RetListCity list : lists) {
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

  @RequestMapping(value = "/getImage")
  public void getImage(
    @RequestParam(value = "imgUrl", required = true) String imgUrl,
    HttpServletResponse response) throws IOException {
    URL url = new URL(imgUrl);

    BufferedImage src = javax.imageio.ImageIO.read(url);

    BufferedImage tag = new BufferedImage(130, 195, BufferedImage.TYPE_INT_RGB);

    tag.getGraphics().drawImage(src.getScaledInstance(130, 195, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ImageIO.write(tag, "PNG", out);

    response.setContentType("image/png");
    OutputStream stream = response.getOutputStream();
    stream.write(out.toByteArray());
    stream.flush();
    stream.close();
  }
}
