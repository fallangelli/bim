package com.family.grabserver.model.mtime;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.mtime.CinemamoiveMtimePipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl(value = "http://m.mtime.cn/[\\w\\W]*")
public class CinemamovieMtimeModel implements AfterExtractor {

  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl("cinemaId=(\\d*)")
  private String cinemaid = "";

  @ExtractByUrl
  private String url;

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemamoiveMtimePipeline pipeline = applicationContext.getBean(CinemamoiveMtimePipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000)
      , pipeline, CinemamovieMtimeModel.class)
      .addUrl("http://m.mtime.cn/Service/callback.mi/Showtime/ShowtimeMovieAndDateListByCinema.api?cinemaId=1813")
      .thread(1).run();
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getCinemaid() {
    return cinemaid;
  }

  public void setCinemaid(String cinemaid) {
    this.cinemaid = cinemaid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    String retVal = context;
    return retVal;
  }

  @Override
  public void afterProcess(Page page) {
  }

}
