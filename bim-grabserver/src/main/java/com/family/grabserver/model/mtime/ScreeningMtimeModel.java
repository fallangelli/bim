package com.family.grabserver.model.mtime;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.mtime.ScreeningMtimePipeline;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl("http://m.mtime.cn/Service/[\\w\\W]*")
public class ScreeningMtimeModel implements AfterExtractor {

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl("cinemaId=(\\d*)")
  private String cinemaId = "";

  @ExtractByUrl("movieId=(\\d*)")
  private String movieId = "";

  @ExtractByUrl("date=(\\d*-\\d*-\\d*)")
  private String showDate = "";

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final ScreeningMtimePipeline pipeline = applicationContext.getBean(ScreeningMtimePipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(3),
      pipeline, ScreeningMtimeModel.class)
      .addUrl("http://m.mtime.cn/Service/callback.mi/Showtime/ShowtimeMovieAndDateListByCinema.api?cinemaId=3275&movieId=216885&date=2016-01-21").thread(1).run();
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(String cinemaId) {
    this.cinemaId = cinemaId;
  }

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }

  public String getShowDate() {
    return showDate;
  }

  public void setShowDate(String showDate) {
    this.showDate = showDate;
  }

//    @Override
//    public String toString() {
//        String retVal = context;
//        return retVal;
//    }

  @Override
  public void afterProcess(Page page) {
    if (context.trim().length() < 5) {
      page.addTargetRequest(page.getRequest());
      context = null;
    }
  }

}
