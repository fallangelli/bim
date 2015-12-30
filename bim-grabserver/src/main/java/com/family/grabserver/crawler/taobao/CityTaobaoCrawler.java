package com.family.grabserver.crawler.taobao;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.model.taobao.CityTaobaoModel;
import com.family.grabserver.pipeline.taobao.CityTaobaoPipeline;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class CityTaobaoCrawler {
  @Autowired
  private CityTaobaoPipeline cityPipeline;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) throws IOException {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CityTaobaoCrawler jsonCrawler = applicationContext.getBean(CityTaobaoCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    logger.info("开始抓取 淘宝 城市列表");
    try {
      Properties prop = new Properties();
      InputStream in = getClass().getResourceAsStream("/grabConfig.properties");
      prop.load(in);

      String t = prop.getProperty("t_getAllRegion");
      String sign = prop.getProperty("sign_getAllRegion");

      String tk = prop.getProperty("tk");
      String enc = prop.getProperty("enc");


      String url = "http://api.m.taobao.com/h5/mtop.film.mtopregionapi.getallregion/4.0/?" +
        "v=4.0&api=mtop.film.MtopRegionAPI.getAllRegion&appKey=12574478" +
        "&t=" + t +
        "&callback=mtopjsonp6&type=jsonp" +
        "&sign=" + sign +
        "&data=" + java.net.URLEncoder.encode("{\"platform\":\"8\"}", "utf-8");

      OOSpider.create(Site.me().setTimeOut(30000)
          .addCookie("_m_h5_tk", tk)
          .addCookie("_m_h5_tk_enc", enc)
          .setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
        cityPipeline, CityTaobaoModel.class)
        .addUrl(url).thread(1).run();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
