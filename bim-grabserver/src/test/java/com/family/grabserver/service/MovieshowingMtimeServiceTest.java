package com.family.grabserver.service;

import com.family.grabserver.service.mtime.MovieshowingMtimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-myBatis.xml"})
public class MovieshowingMtimeServiceTest {
  @Autowired
  private MovieshowingMtimeService service;

  @Test
  public void testSelectByPrimaryKey() throws Exception {
    System.out.println(service.selectByPrimaryKey(15615).getName());
  }
}
