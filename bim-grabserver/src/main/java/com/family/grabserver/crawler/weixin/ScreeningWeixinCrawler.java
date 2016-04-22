package com.family.grabserver.crawler.weixin;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.bim_grab.CinemaWeixin;
import com.family.grabserver.model.Weixin.ScreeningWeixinModel;
import com.family.grabserver.pipeline.weixin.ScreeningWeixinPipeline;
import com.family.grabserver.service.weixin.CinemaWeixinService;
import com.family.grabserver.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScreeningWeixinCrawler {
  @Autowired
  private ScreeningWeixinPipeline pipeline;

  @Autowired
  private CinemaWeixinService cinemaService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final ScreeningWeixinCrawler jsonCrawler = applicationContext.getBean(ScreeningWeixinCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {

    try {
      SqlUtil.truncateTable("screening_weixin");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    List<CinemaWeixin> allCinemas = cinemaService.selectAll();

    List<String> urls = new ArrayList<>();
    for (CinemaWeixin cinema : allCinemas) {
      urls.add("http://m.wepiao.com/data/v5/cinemas/cities/" + cinema.getWeixinCityId()
        + "/sched_city_cinema_" + cinema.getWeixinCityId() + "_" + cinema.getId() + ".json?" +
        "cityId=" + cinema.getWeixinCityId() + "&cinemaId=" + cinema.getId());
    }
    logger.info("开始抓取 微信 电影 信息");
    OOSpider.create(Site.me().setCharset("UTF-8").setTimeOut(60000).setSleepTime(100).setCycleRetryTimes(5).setRetrySleepTime(3000),
      pipeline, ScreeningWeixinModel.class)
      .addUrl((String[]) urls.toArray(new String[]{}))
      .thread(80).run();

  }
}
