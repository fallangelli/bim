package com.family.grabserver.crawler.maoyan;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.bim_grab.CinemaMaoyan;
import com.family.grabserver.model.maoyan.CinemamovieMaoyanModel;
import com.family.grabserver.pipeline.maoyan.CinemamoiveMaoyanPipeline;
import com.family.grabserver.service.CinemaMaoyanService;
import com.family.grabserver.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CinemamovieMaoyanCrawler {
  @Autowired
  private CinemamoiveMaoyanPipeline pipeline;

  @Autowired
  private CinemaMaoyanService cinemaService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemamovieMaoyanCrawler jsonCrawler = applicationContext.getBean(CinemamovieMaoyanCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    try {
      SqlUtil.truncateTable("cinemamovie_maoyan");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
    List<CinemaMaoyan> allCinema = cinemaService.selectAll();
    List<String> urls = new ArrayList<String>();
    for (CinemaMaoyan cinema : allCinema) {
      String url = "http://m.maoyan.com/showtime/wrap.json?cinemaid=" + cinema.getId();
      urls.add(url);
    }
    logger.info("开始抓取 猫眼 影院上映电影");
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(1000),
      pipeline, CinemamovieMaoyanModel.class).addUrl((String[]) urls.toArray(new String[]{}))
      .thread(100).run();
  }
}
