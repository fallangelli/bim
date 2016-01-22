package com.family.grabserver.crawler.baidu;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.bim_grab.CityareaBaidu;
import com.family.grabserver.model.baidu.CinemaBaiduModel;
import com.family.grabserver.pipeline.baidu.CinemaBaiduPipeline;
import com.family.grabserver.service.CityareaBaiduService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CinemaBaiduCrawler {
  @Autowired
  private CinemaBaiduPipeline pipeline;

  @Autowired
  private CityareaBaiduService areaService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemaBaiduCrawler jsonCrawler = applicationContext.getBean(CinemaBaiduCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {

    List<CityareaBaidu> allAreas = areaService.selectAll();

    List<String> urls = new ArrayList<String>();
    for (CityareaBaidu area : allAreas) {
      urls.add("http://m.dianying.baidu.com/api/portal/loadMoreCinema?" +
        "c=" + area.getCityId() + "&areaId=" + area.getId() + "&cityName=" + area.getCityName() +
        "&areaName=" + area.getName() + "&pageSize=1000&pageNum=0");
    }
    logger.info("开始抓取 百度 影院详情信息");
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(100).setCycleRetryTimes(5).setRetrySleepTime(3000),
      pipeline, CinemaBaiduModel.class)
      .addUrl((String[]) urls.toArray(new String[]{}))
      .thread(50).run();

  }
}
