package com.family.grabserver.crawler.taobao;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.CityTaobao;
import com.family.grabserver.model.taobao.CinemaTaobaoModel;
import com.family.grabserver.pipeline.taobao.CinemaTaobaoPipeline;
import com.family.grabserver.service.CityTaobaoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class CinemaTaobaoCrawler {
  @Autowired
  private CinemaTaobaoPipeline cinemaTaobaoPipeline;

  @Autowired
  private CityTaobaoService cityService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemaTaobaoCrawler jsonCrawler = applicationContext.getBean(CinemaTaobaoCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    try {
      List<CityTaobao> cities = cityService.selectAll();
      Properties prop = new Properties();
      InputStream in = getClass().getResourceAsStream("/grabConfig.properties");
      prop.load(in);

      String t = prop.getProperty("t_getMixupCinemas");
      String sign = prop.getProperty("sign_getMixupCinemas");

      String tk = prop.getProperty("tk");
      String enc = prop.getProperty("enc");

      List<String> urls = new ArrayList<String>();
      for (CityTaobao city : cities) {
        String baseUrl = "http://api.m.taobao.com/h5/mtop.film.mtopcinemaapi.getmixupcinemas/4.0/?v=4.0" +
          "&api=mtop.film.MtopCinemaAPI.getMixupCinemas&appKey=12574478" +
          "&t=" + t + "&callback=mtopjsonp1&type=jsonp" +
          "&sign=" + sign +
          "&cityId=" + city.getId() +
          "&data=";

        String data = java.net.URLEncoder.encode("{\"platform\":\"8\",\"asac\":\"D679AU6J95PHQT67G0B5\"," +
          "\"longitude\":0,\"latitude\":0,\"supportTypeCode\":3,\"" +
          "cityCode\":\"" + city.getId() + "\"}", "utf-8");
        logger.info("开始抓取 时光 影院详情信息");
        OOSpider.create(Site.me().setTimeOut(60000)
            .addCookie("_m_h5_tk", tk)
            .addCookie("_m_h5_tk_enc", enc)
            .setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
          cinemaTaobaoPipeline, CinemaTaobaoModel.class)
          .addUrl(baseUrl + data)
//          .addUrl((String[]) urls.toArray(new String[]{}))
          .thread(1).run();

        urls.add(baseUrl + data);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
