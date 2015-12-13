package com.family.webserver.service;

import com.family.webserver.entity.HomeCityHotMovie;
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
  public void testGetHotMoviesByCity() throws Exception {
    List<List<HomeCityHotMovie>> movies = service.getHotMoviesByCity(2867);
    System.out.println(movies);
  }
}
