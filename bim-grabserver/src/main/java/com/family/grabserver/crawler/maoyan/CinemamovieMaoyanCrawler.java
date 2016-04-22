package com.family.grabserver.crawler.maoyan;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.bim_grab.CinemaMaoyan;
import com.family.grabserver.entity.bim_grab.CityMaoyan;
import com.family.grabserver.model.maoyan.CinemamovieMaoyanModel;
import com.family.grabserver.pipeline.maoyan.CinemamoiveMaoyanPipeline;
import com.family.grabserver.service.maoyan.CinemaMaoyanService;
import com.family.grabserver.service.maoyan.CityMaoyanService;
import com.family.grabserver.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class CinemamovieMaoyanCrawler {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CinemamoiveMaoyanPipeline pipeline;

  @Autowired
  private CinemaMaoyanService cinemaService;

  @Autowired
  private CityMaoyanService cityService;


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

    ExecutorService pool = Executors.newFixedThreadPool(1);
    List<CityMaoyan> allCity = cityService.selectAll();

    logger.info("开始抓取 猫眼 影院排片信息");

    for (CityMaoyan city : allCity) {
      CinemamovieMaoyanCrawler.CinemamovieThread th = new CinemamovieThread(city.getId());
      pool.execute(th);
    }
    pool.shutdown();
    try {//等待直到所有任务完成
      pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    logger.info("完成抓取 猫眼 影院排片信息");
  }

  public class CinemamovieThread implements Runnable {
    Integer cityId;

    public CinemamovieThread(Integer cityId) {
      this.cityId = cityId;
    }

    @Override
    public void run() {
      List<CinemaMaoyan> allCinema = cinemaService.selectByMaoyanCityId(cityId);
//      CookieSimProcessor cookieSimer = new CookieSimProcessor(cityId);
      List<String> urls = new ArrayList<>();
      for (CinemaMaoyan cinema : allCinema) {
        String url = "http://m.maoyan.com/showtime/wrap.json?cinemaid=" + cinema.getId();
        urls.add(url);
      }

      Site site = Site.me().setTimeOut(60000).setSleepTime(5000)
        .setCycleRetryTimes(3).setRetrySleepTime(2000);
//      for (Cookie cookie : cookieSimer.getCookieStore().getCookies()) {
//        site.addCookie(cookie.getName(), cookie.getValue());
//      }
      site.addCookie("ci", cityId.toString());

      OOSpider.create(site,
        pipeline, CinemamovieMaoyanModel.class).addUrl((String[]) urls.toArray(new String[]{}))
        .thread(5).run();
    }

    public Integer getCityId() {
      return cityId;
    }

    public void setCityId(Integer cityId) {
      this.cityId = cityId;
    }


  }
}
