package com.family.webserver.service;

import com.family.webserver.entity.Cinema;
import com.family.webserver.entity.ListCinema;
import com.family.webserver.entity.MovieCinema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-myBatis.xml"})
public class CityCinemaServiceTest {

  @Autowired
  private CityCinemaService service;

  @Test
  public void testSelect() throws Exception {
    Cinema cinema = service.getCinemaById(212213);
    System.out.println(cinema.getName());
  }

  @Test
  public void testGetByCinemaId() throws Exception {

  }

  @Test
  public void testGetCinemaByCity() throws Exception {
    List<ListCinema> listCinemas = service.getCityCinemas(110000, "39.91488908", "116.40387397", "len",
      null, null, 1, 10);
    System.out.println("return size : " + listCinemas.size());
    listCinemas = service.getCityCinemas(110000, "39.91488908", "116.40387397", "minPrice",
      null, null, 0, 10);
    System.out.println("return size : " + listCinemas.size());

    listCinemas = service.getCityCinemas(110000, "39.91488908", "116.40387397", "minPrice",
      1, null, 0, 10);
    System.out.println("return size : " + listCinemas.size());

    listCinemas = service.getCityCinemas(110000, "39.91488908", "116.40387397", "minPrice",
      null, "东", 0, 10);
    System.out.println("return size : " + listCinemas.size());
  }

  @Test
  public void testGetMovieCinemaByDate() throws Exception {
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-12");
    List<MovieCinema> listCinemas = service.getMovieCinemaByDate(110000, 1713, date, "11", "11");


    System.out.println("return size : " + listCinemas.size());
  }
}
