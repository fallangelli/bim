package com.family.webserver.service;

import com.family.webserver.entity.CinemaScreening;
import com.family.webserver.entity.CityMovieWithShowDates;
import com.family.webserver.entity.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
  "classpath:applicationContext-myBatis.xml"})
public class CinemaScreeningServiceTest {
  @Autowired
  private CityScreeningService service;


  @Test
  public void testGetMovieCinemaShowDatesByCity() throws Exception {
    CityMovieWithShowDates dates = service.getCityMovieWithShowDates(1, 1554, null);
    System.out.println("return size : " + dates);
  }

  @Test
  public void testGetCityScreening() throws Exception {
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-26");

    CinemaScreening screening = service.getCinemaScreening(578, 2256, date);
    System.out.println("return size : " + screening);
  }


  @Test
  public void testGetCitySource() throws Exception {
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-24");
    Date time = new SimpleDateFormat("HH:mm:ss").parse("11:45:00");
    List<Source> screening = service.getCinemaSource(578, 2256, date, time);
    System.out.println("return size : " + screening.size());
  }
}
