package com.family.grabserver.crawler.weixin;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.model.Weixin.CityWeixinModel;
import com.family.grabserver.pipeline.weixin.CityWeixinPipeline;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class CityWeixinCrawler {
  @Autowired
  private CityWeixinPipeline pipeline;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CityWeixinCrawler jsonCrawler = applicationContext.getBean(CityWeixinCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    logger.info("开始抓取 微信 城市列表");

    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      pipeline, CityWeixinModel.class)
      .addUrl("http://m.wepiao.com/data/v5/city.json")
      .thread(1).run();
  }
}

