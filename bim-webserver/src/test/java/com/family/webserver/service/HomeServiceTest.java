package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.RetHotMovie;
import com.family.webserver.entity.RetListCity;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-myBatis.xml"})
public class HomeServiceTest {
  @Autowired
  private HomeService service;


  @Ignore
  public void testGetCityInfoFromName() {
    Integer cityId = service.getCityIdFromName("北京市");
    System.out.println(cityId);
  }

  @Test
  public void testGetHotMoviesByCity() {
    List<RetHotMovie> movies = service.getHotMoviesByCity(2861);
    System.out.println(movies);
  }


  @Test
  public void testGetNearCinemas() {

    List<Cinema> cinemas = service.getNearCinemas(140200, "40.0759560000", "113.3609470000");
    String out = "";
    for (Cinema cinema : cinemas) {
      out += cinema.getName() + "  ";
    }
    System.out.println(out);

    out = "";
    cinemas = service.getNearCinemas(110000, "40.0255860000", "116.3714920000");
    for (Cinema cinema : cinemas) {
      out += cinema.getName() + "  ";
    }
    System.out.println(out);

    out = "";
    cinemas = service.getNearCinemas(110000, "39.8312680000", "116.4074240000");
    for (Cinema cinema : cinemas) {
      out += cinema.getName() + "  ";
    }
    System.out.println(out);
  }

  @Ignore
  public void testGetAllCities() {
    List<RetListCity> cities = service.getAllCities();
    System.out.println(cities);
  }

}
