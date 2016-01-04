package com.family.grabserver.crawler.baidu;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.CityBaidu;
import com.family.grabserver.model.baidu.CityBaiduModel;
import com.family.grabserver.model.baidu.CityareaBaiduModel;
import com.family.grabserver.pipeline.baidu.CityBaiduPipeline;
import com.family.grabserver.pipeline.baidu.CityareaBaiduPipeline;
import com.family.grabserver.service.CityBaiduService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityBaiduCrawler {
  @Autowired
  private CityBaiduPipeline pipeline;
  @Autowired
  private CityareaBaiduPipeline areaPipeline;
  @Autowired
  private CityBaiduService service;


  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CityBaiduCrawler jsonCrawler = applicationContext.getBean(CityBaiduCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    logger.info("开始抓取 百度 城市列表");

    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      pipeline, CityBaiduModel.class)
      .addUrl("http://m.dianying.baidu.com/city/choose")
      .thread(1).run();

    List<CityBaidu> cities = service.selectAll();
    List<String> urls = new ArrayList<String>();
    for (CityBaidu city : cities) {
      urls.add("http://m.dianying.baidu.com/info/cinema/nearby?sfrom=newnuomi&from=webapp&" +
        "&c=" + city.getId() + "&district=%E5%85%A8%E9%83%A8%E5%95%86%E5%9C%88");
    }

    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      areaPipeline, CityareaBaiduModel.class)
      .addUrl((String[]) urls.toArray(new String[]{}))
      .thread(30).run();
  }
}

