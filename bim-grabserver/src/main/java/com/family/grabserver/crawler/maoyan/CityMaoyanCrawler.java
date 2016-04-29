package com.family.grabserver.crawler.maoyan;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.model.maoyan.CityMaoyanModel;
import com.family.grabserver.pipeline.maoyan.CityMaoyanPipeline;
import com.google.common.collect.Lists;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CityMaoyanCrawler {
  @Autowired
  private CityMaoyanPipeline CityMaoyanPipeline;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CityMaoyanCrawler jsonCrawler = applicationContext.getBean(CityMaoyanCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    logger.info("开始抓取 猫眼 城市列表");
//    CookieSimProcessor cookieSimer = new CookieSimProcessor(1);
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000)
        .setHttpProxyPool(Lists.newArrayList(
          new String[]{"36.233.123.115", "8080"},
          new String[]{"116.23.72.205", "9999"})),
      CityMaoyanPipeline, CityMaoyanModel.class)
      .addUrl("http://m.maoyan.com/changecity.json")
      .thread(1).run();
  }
}
