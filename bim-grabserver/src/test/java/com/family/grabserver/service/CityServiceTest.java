package com.family.grabserver.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-myBatis.xml"})
public class CityServiceTest {
  @Autowired
  private CityService service;

  @Test
  public void testGetMostSimilarCity() throws Exception {


    System.out.println(service.getMostSimilarCity("陵水").getName());
  }


  @Test
  public void testeGetMostSimilarArea() throws Exception {
    System.out.println(service.getMostSimilarArea(422800, "恩施", "恩施").getName());
  }
}
