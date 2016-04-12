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

    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      cityPipeline, CityTaobaoModel.class)
      .addUrl("http://m.mtime.cn/Service/callback.mi/Showtime/HotCitiesByCinema.api")
      .thread(1).run();

  }
}
