package com.family.grabserver.model.maoyan;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import org.slf4j.LoggerFactory;


public class ScreeningMaoyanModel implements AfterExtractor {

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @ExtractBy(value = "/html/body")
  private String context;

  @ExtractByUrl("cinemaid=(\\d*)")
  private String cinemaid = "";

  @ExtractByUrl("movieid=(\\d*)")
  private String movieid = "";


  public static void main(String[] args) {
//    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
//    final ScreeningMaoyanPipeline pipeline = applicationContext.getBean(ScreeningMaoyanPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(3),
      new ConsolePageModelPipeline(), ScreeningMaoyanModel.class)
      .addUrl("http://m.maoyan.com/#tmp=showtime&cinemaid=48&movieid=246063&date=2016-02-26").thread(1).run();
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

  public String getMovieid() {
    return movieid;
  }

  public void setMovieid(String movieid) {
    this.movieid = movieid;
  }

  @Override
  public void afterProcess(Page page) {
    context = context.replace("<body>", "");
    context = context.replace("</body>", "");
    context = context.replace("\n", "");
  }

}
