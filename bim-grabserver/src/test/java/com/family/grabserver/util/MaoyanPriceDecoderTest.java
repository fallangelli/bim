package com.family.grabserver.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/12/4.
 */
public class MaoyanPriceDecoderTest {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testDecode() throws Exception {

    String html = " <span class='m3' true2=''><i>6731214</i></span>. <span class='m3' true1=''><i>3286</i></span>";
    String css = "ext-decoration: inherit;}.m3>i{overflow:hidden;display:inline-block;font-style:normal;font-family:arial;text-decoration:inherit;vertical-align:top;}\n" +
      ".true0>i:nth-of-type(1){text-indent:-0.55em;width:0.0em;}\n" +
      ".true1>i:nth-of-type(1){text-indent:-1.1em;width:0.55em;}\n" +
      ".true2>i:nth-of-type(1){text-indent:-1.6500000000000001em;width:1.1em;}\n" +
      ".true3>i:nth-of-type(1){text-indent:-0.55em;width:1.1em;}\n" +
      ".true3>i:nth-of-type(2){text-indent:-0.0em;width:0.55em;}\n" +
      ".true4>i:nth-of-type(1){text-indent:-1.1em";

    float price = MaoyanPriceDecoder.decode(html, css);
    System.out.println(price);

  }
}
