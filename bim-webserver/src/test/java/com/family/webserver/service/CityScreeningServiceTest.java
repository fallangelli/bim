package com.family.webserver.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
  "classpath:applicationContext-myBatis.xml"})
public class CityScreeningServiceTest {
  @Autowired
  private CityScreeningService service;


  @Test
  public void testGetMovieCinemaShowDatesByCity() throws Exception {
    List<String> dates = service.getMovieCinemaShowDatesByCity(2861, 1554, 933);
    System.out.println("return size : " + dates.size());
  }
}
