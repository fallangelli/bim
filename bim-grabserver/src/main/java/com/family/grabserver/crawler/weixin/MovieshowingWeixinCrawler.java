package com.family.grabserver.crawler.weixin;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.bim_grab.CityWeixin;
import com.family.grabserver.model.Weixin.MovieshowingWeixinModel;
import com.family.grabserver.pipeline.weixin.MovieshowingWeixinPipeline;
import com.family.grabserver.service.weixin.CityWeixinService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieshowingWeixinCrawler {
  @Autowired
  private MovieshowingWeixinPipeline pipeline;

  @Autowired
  private CityWeixinService cityService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final MovieshowingWeixinCrawler jsonCrawler = applicationContext.getBean(MovieshowingWeixinCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {

    List<CityWeixin> allCities = cityService.selectAll();

    List<String> urls = new ArrayList<>();
    for (CityWeixin city : allCities) {
      urls.add("http://m.wepiao.com/data/v5/movies/cities/" + city.getId() +
        "/movies_city_" + city.getId() + ".json?cityId=" + city.getId() + "cityName=" + city.getName());
    }
    logger.info("开始抓取 微信 电影 信息");
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(100).setCycleRetryTimes(5).setRetrySleepTime(3000),
      pipeline, MovieshowingWeixinModel.class)
      .addUrl((String[]) urls.toArray(new String[]{}))
      .thread(20).run();

  }
}
