package com.family.webserver.service;

import com.family.webserver.entity.CityHotMovie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-myBatis.xml"})
public class CityMovieServiceTest {

  @Autowired
  private CityMovieService service;

  @Test
  public void testGetCityMoives() throws Exception {
    List<CityHotMovie> movies = service.getCityMoives(1);
    System.out.println(movies);
  }
}
