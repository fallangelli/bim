package com.family.grabserver.crawler.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.CinemamovieMtime;
import com.family.grabserver.model.mtime.MovieshowingMtimeModel;
import com.family.grabserver.model.mtime.ScreeningMtimeModel;
import com.family.grabserver.pipeline.mtime.MovieshowingMtimePipeline;
import com.family.grabserver.pipeline.mtime.ScreeningMtimePipeline;
import com.family.grabserver.service.CinemamovieMtimeService;
import com.family.grabserver.service.MovieshowingMtimeService;
import com.family.grabserver.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScreeningMtimeCrawler {
  @Autowired
  private ScreeningMtimePipeline pipeline;
  @Autowired
  private MovieshowingMtimePipeline movieShowingMtimePipeline;

  @Autowired
  private MovieshowingMtimeService movieService;
  @Autowired
  private CinemamovieMtimeService cinemaMovieService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final ScreeningMtimeCrawler jsonCrawler = applicationContext.getBean(ScreeningMtimeCrawler.class);
    jsonCrawler.crawl();
    System.exit(0);
  }

  public void crawl() {
    try {
      SqlUtil.truncateTable("screening_Mtime");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    List<CinemamovieMtime> cmList = cinemaMovieService.selectAll();


    List<String> urls = new ArrayList<>();
    for (CinemamovieMtime cm : cmList) {
      //电影不存在则添加
      if (movieService.selectByPrimaryKey(cm.getMovieId()) == null) {
        logger.info("添加 时光 电影基本信息 - " + cm.getMovieId());
        OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
          movieShowingMtimePipeline, MovieshowingMtimeModel.class)
          .addUrl("http://m.mtime.cn/Service/callback.mi/movie/Detail.api?movieId=" + cm.getMovieId())
          .thread(1).run();
      }
      JSONArray dates = JSON.parseArray(cm.getShowDates());
      for (Object dateOb : dates) {
        String date = (String) dateOb;
        String movieUrl = "http://m.mtime.cn/Service/callback.mi/showtime/ShowTimesByCinemaMovieDate.api?cinemaId=" +
          +cm.getCinemaId() + "&movieId=" + cm.getMovieId() +
          "&date=" + date;
        urls.add(movieUrl);
      }
    }
    logger.info("开始抓取 时光 场次信息");
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      pipeline, ScreeningMtimeModel.class)
      .addUrl((String[]) urls.toArray(new String[]{}))
      .thread(1000).run();
  }

}
