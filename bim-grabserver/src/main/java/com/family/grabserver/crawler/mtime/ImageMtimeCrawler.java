package com.family.grabserver.crawler.mtime;

import com.family.grab.Site;
import com.family.grab.model.OOSpider;
import com.family.grabserver.entity.bim_grab.MovieshowingMtime;
import com.family.grabserver.model.mtime.ImageMtimeModel;
import com.family.grabserver.pipeline.mtime.ImageMtimePipeline;
import com.family.grabserver.service.mtime.MovieshowingMtimeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImageMtimeCrawler {
  @Autowired
  private ImageMtimePipeline imagePipeline;
  @Autowired
  private MovieshowingMtimeService movieService;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final ImageMtimeCrawler jsonCrawler = applicationContext.getBean(ImageMtimeCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    logger.info("开始抓取 时光 剧照");

    List<MovieshowingMtime> allMovies = movieService.selectAll();

    List<String> urls = new ArrayList<String>();
    for (MovieshowingMtime movie : allMovies) {
      urls.add("http://m.mtime.cn/Service/callback.mi/movie/Image.api?" +
        "movieId=" + movie.getId());
    }

    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      imagePipeline, ImageMtimeModel.class)
      .addUrl((String[]) urls.toArray(new String[]{}))
      .thread(30).run();
  }
}
