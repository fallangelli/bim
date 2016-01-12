package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.CityHotMovie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2015/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-myBatis.xml"})
public class HomeServiceTest {
  @Autowired
  private HomeService service;

  @Test
  public void testGetHotMoviesByCity() {
    List<List<CityHotMovie>> movies = service.getHotMoviesByCity(2861);
    System.out.println(movies);
  }


  @Test
  public void testGetNearCinemas() {
    List<Cinema> cinemas = service.getNearCinemas("23.02362", "113.1147");
    System.out.println(cinemas);
  }
}
