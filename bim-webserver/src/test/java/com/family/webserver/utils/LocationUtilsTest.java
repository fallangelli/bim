package com.family.webserver.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2016/1/5.
 */
public class LocationUtilsTest {

  @Test
  public void testGetDistance() throws Exception {
    double a = LocationUtils.getDistance(39.91488908, 116.40387397, 39.95869, 116.2914);
    System.out.println(a);
  }
}
