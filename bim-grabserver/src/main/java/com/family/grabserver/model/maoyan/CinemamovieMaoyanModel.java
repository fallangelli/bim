package com.family.grabserver.model.maoyan;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.maoyan.CinemamoiveMaoyanPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl(value = "http://m.maoyan.com/[\\w\\W]*")
public class CinemamovieMaoyanModel implements AfterExtractor {

  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl("cinemaid=(\\d*)")
  private String cinemaid = "";

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemamoiveMaoyanPipeline pipeline = applicationContext.getBean(CinemamoiveMaoyanPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000)
      , pipeline, CinemaMaoyanModel.class)
      .addUrl("http://m.maoyan.com/showtime/wrap.json?cinemaid=721")
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

  @Override
  public String toString() {
    String retVal = context;
    return retVal;
  }

  @Override
  public void afterProcess(Page page) {

  }

}
