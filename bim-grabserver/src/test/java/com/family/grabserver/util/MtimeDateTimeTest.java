package com.family.grabserver.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2015/12/4.
 */
public class MtimeDateTimeTest {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testDecode() throws Exception {
    long dateStr = 1449583200;

    Calendar beijingcal = new GregorianCalendar();
    beijingcal.clear();
    int zoneOffset = beijingcal.get(java.util.Calendar.ZONE_OFFSET);
    beijingcal.setTimeInMillis(dateStr * 1000 - zoneOffset);


    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    System.out.println(fmt.format(beijingcal.getTime()));

  }
}
