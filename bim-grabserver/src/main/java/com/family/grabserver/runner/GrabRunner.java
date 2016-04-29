package com.family.grabserver.runner;

import com.family.grabserver.crawler.baidu.CinemaBaiduCrawler;
import com.family.grabserver.crawler.baidu.CityBaiduCrawler;
import com.family.grabserver.crawler.baidu.ScreeningBaiduCrawler;
import com.family.grabserver.crawler.maoyan.CinemaMaoyanCrawler;
import com.family.grabserver.crawler.maoyan.CinemamovieMaoyanCrawler;
import com.family.grabserver.crawler.maoyan.CityMaoyanCrawler;
import com.family.grabserver.crawler.maoyan.ScreeningMaoyanCrawler;
import com.family.grabserver.crawler.mtime.*;
import com.family.grabserver.crawler.weixin.CinemaWeixinCrawler;
import com.family.grabserver.crawler.weixin.CityWeixinCrawler;
import com.family.grabserver.crawler.weixin.MovieshowingWeixinCrawler;
import com.family.grabserver.crawler.weixin.ScreeningWeixinCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GrabRunner {
  @Autowired
  public CityMaoyanCrawler cityMaoyanCrawler;
  @Autowired
  public CinemaMaoyanCrawler cinemaMaoyanCrawler;
  @Autowired
  public CinemamovieMaoyanCrawler cinemamovieMaoyanCrawler;
  @Autowired
  public ScreeningMaoyanCrawler screeningMaoyanCrawler;

  @Autowired
  public CityMtimeCrawler cityMtimeCrawler;
  @Autowired
  public CinemaMtimeCrawler cinemaMtimeCrawler;
  @Autowired
  public CinemamovieMtimeCrawler cinemamovieMtimeCrawler;
  @Autowired
  public ScreeningMtimeCrawler screeningMtimeCrawler;
  @Autowired
  public CommentMtimeCrawler commentMtimeCrawler;
  @Autowired
  public ImageMtimeCrawler imageMtimeCrawler;


  @Autowired
  public CityBaiduCrawler cityBaiduCrawler;
  @Autowired
  public CinemaBaiduCrawler cinemaBaiduCrawler;
  @Autowired
  public ScreeningBaiduCrawler screeningBaiduCrawler;

  @Autowired
  public CityWeixinCrawler cityWeixinCrawler;
  @Autowired
  public CinemaWeixinCrawler cinemaWeixinCrawler;
  @Autowired
  public MovieshowingWeixinCrawler movieshowingWeixinCrawler;
  @Autowired
  public ScreeningWeixinCrawler screeningWeixinCrawler;


  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final GrabRunner grabRunner = applicationContext.getBean(GrabRunner.class);
    try {
      grabRunner.grabCity(1);
      //    grabRunner.grabCinema();
//    grabRunner.grabCinemamovie();
//    grabRunner.grabScreening();
//    grabRunner.mergeService.merge();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void grabCity(int source) throws Exception {
    switch (source) {
      case 0:
        cityMaoyanCrawler.crawl();
        cityMtimeCrawler.crawl();
        cityBaiduCrawler.crawl();
        cityWeixinCrawler.crawl();
        break;
      case 1:
        cityMtimeCrawler.crawl();
        break;
      case 2:
        cityBaiduCrawler.crawl();
        break;
      case 3:
        cityWeixinCrawler.crawl();
        break;
      case 4:
        cityMaoyanCrawler.crawl();
        break;
      default:
        throw new Exception("请选择源，0所有1时光2百度3微信4猫眼");
    }
  }


  public void grabCinema(int source) throws Exception {
    switch (source) {
      case 0:
        cinemaMaoyanCrawler.crawl();
        cinemaMtimeCrawler.crawl();
        cinemaBaiduCrawler.crawl();
        cinemaWeixinCrawler.crawl();
        break;
      case 1:
        cinemaMtimeCrawler.crawl();
        break;
      case 2:
        cinemaBaiduCrawler.crawl();
        break;
      case 3:
        cinemaWeixinCrawler.crawl();
        break;
      case 4:
        cinemaMaoyanCrawler.crawl();
        break;
      default:
        throw new Exception("请选择源，0所有1时光2百度3微信4猫眼");
    }

  }

  public void grabScreening(int source) throws Exception {
    switch (source) {
      case 0:
        cinemamovieMtimeCrawler.crawl();
        screeningMtimeCrawler.crawl();

        cinemamovieMaoyanCrawler.crawl();
        screeningMaoyanCrawler.crawl();

        screeningBaiduCrawler.crawl();

        movieshowingWeixinCrawler.crawl();
        screeningWeixinCrawler.crawl();
        break;
      case 1:
        cinemamovieMtimeCrawler.crawl();
        screeningMtimeCrawler.crawl();
        break;
      case 2:
        screeningBaiduCrawler.crawl();
        break;
      case 3:
        movieshowingWeixinCrawler.crawl();
        screeningWeixinCrawler.crawl();
        break;
      case 4:
        cinemamovieMaoyanCrawler.crawl();
        screeningMaoyanCrawler.crawl();
        break;
      default:
        throw new Exception("请选择源，0所有1时光2百度3微信4猫眼");
    }

  }

  public void grabMovieContent(int source) throws Exception {
    switch (source) {
      case 0:
        commentMtimeCrawler.crawl();
        imageMtimeCrawler.crawl();
        break;
      case 1:
        commentMtimeCrawler.crawl();
        imageMtimeCrawler.crawl();
        break;
      default:
        throw new Exception("请选择源，0所有1时光");
    }

  }

}
