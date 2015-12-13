package com.family.grabserver.crawler;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grab.pipeline.ConsolePipeline;
import com.family.grabserver.model.DoubanComing;
import com.family.grabserver.pipeline.DoubanComingPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PageCrawler {
  @Qualifier("DoubanComingPipeline")
  @Autowired
  private DoubanComingPipeline dcPipeline;

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final PageCrawler pageCrawler = applicationContext.getBean(PageCrawler.class);
    pageCrawler.crawl();
  }

  public void crawl() {
    OOSpider.create(Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),
      dcPipeline, DoubanComing.class).addUrl("http://movie.douban.com/coming")
      .addPipeline(new ConsolePipeline()).thread(5).run();
  }
}
