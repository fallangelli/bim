package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.RetHotMovie;
import com.family.webserver.entity.RetListCity;
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


  @Test
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
    List<Cinema> cinemas = service.getNearCinemas(1, "23.02362", "113.1147");
    System.out.println(cinemas);
    cinemas = service.getNearCinemas(1, "", "");
    System.out.println(cinemas);
  }

  @Test
  public void testGetAllCities() {
    List<RetListCity> cities = service.getAllCities();
    System.out.println(cities);
  }

}
