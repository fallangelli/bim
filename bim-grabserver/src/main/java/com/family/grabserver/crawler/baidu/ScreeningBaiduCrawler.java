package com.family.grabserver.crawler.baidu;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.CinemaBaidu;
import com.family.grabserver.model.baidu.ScreeningBaiduModel;
import com.family.grabserver.pipeline.baidu.ScreeningBaiduPipeline;
import com.family.grabserver.service.CinemaBaiduService;
import com.family.grabserver.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ScreeningBaiduCrawler {
  @Autowired
  private ScreeningBaiduPipeline pipeline;
  @Autowired
  private CinemaBaiduService cinemaService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final ScreeningBaiduCrawler jsonCrawler = applicationContext.getBean(ScreeningBaiduCrawler.class);
    jsonCrawler.crawl();
    System.exit(0);
  }

  public void crawl() {
    try {
      SqlUtil.deleteAll("screening_baidu");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    List<CinemaBaidu> cList = cinemaService.selectAll();
    List<String> urls = new ArrayList<>();
    for (CinemaBaidu cm : cList) {
      String movieUrl = "http://m.dianying.baidu.com/info/cinema/detail?cinemaId=" + cm.getId();
      urls.add(movieUrl);
    }

    logger.info("开始抓取 猫眼 场次信息");
    OOSpider.create(Site.me().setTimeOut(30000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      pipeline, ScreeningBaiduModel.class)
      .addUrl((String[]) urls.toArray(new String[]{}))
      .thread(200).run();
  }

}
