package com.family.grabserver.runner;

import com.family.grabserver.crawler.baidu.CinemaBaiduCrawler;
import com.family.grabserver.crawler.baidu.CityBaiduCrawler;
import com.family.grabserver.crawler.baidu.ScreeningBaiduCrawler;
import com.family.grabserver.crawler.maoyan.CinemaMaoyanCrawler;
import com.family.grabserver.crawler.maoyan.CinemamovieMaoyanCrawler;
import com.family.grabserver.crawler.maoyan.CityMaoyanCrawler;
import com.family.grabserver.crawler.maoyan.ScreeningMaoyanCrawler;
import com.family.grabserver.crawler.mtime.CinemaMtimeCrawler;
import com.family.grabserver.crawler.mtime.CinemamovieMtimeCrawler;
import com.family.grabserver.crawler.mtime.CityMtimeCrawler;
import com.family.grabserver.crawler.mtime.ScreeningMtimeCrawler;
import com.family.grabserver.service.SolidifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainRunner {
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
  public CityBaiduCrawler cityBaiduCrawler;
  @Autowired
  public CinemaBaiduCrawler cinemaBaiduCrawler;
  @Autowired
  public ScreeningBaiduCrawler screeningBaiduCrawler;

  @Autowired
  public SolidifyService mergeService;

  public static void main(String[] args) {
//    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
//    final MainRunner grabRunner = applicationContext.getBean(MainRunner.class);
//    grabRunner.grabCity();
//    grabRunner.grabCinema();
//    grabRunner.grabCinemamovie();
//    grabRunner.grabScreening();
//    grabRunner.mergeService.merge();

    String springConfig = "spring/batch/jobs/job-quartz.xml";

    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/job-config.xml");
  }

  public void execute() {
    System.out.println("[JOB] Welcome to Quartz!");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final MainRunner grabRunner = applicationContext.getBean(MainRunner.class);
    grabRunner.grabCity();
    grabRunner.grabCinema();
    grabRunner.grabCinemamovie();
    grabRunner.grabScreening();
    grabRunner.mergeService.merge();
  }

  public void grabCity() {
//    cityMaoyanCrawler.crawl();
    cityMtimeCrawler.crawl();
    cityBaiduCrawler.crawl();
  }


  public void grabCinema() {
//    cinemaMaoyanCrawler.crawl();
    cinemaMtimeCrawler.crawl();
    cinemaBaiduCrawler.crawl();
  }

  public void grabCinemamovie() {
//    cinemamovieMaoyanCrawler.crawl();
    cinemamovieMtimeCrawler.crawl();
  }

  public void grabScreening() {
//    screeningMaoyanCrawler.crawl();
    screeningMtimeCrawler.crawl();
    screeningBaiduCrawler.crawl();
  }

}
