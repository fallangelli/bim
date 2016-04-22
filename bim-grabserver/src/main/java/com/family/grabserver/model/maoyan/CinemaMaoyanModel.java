package com.family.grabserver.model.maoyan;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.maoyan.CinemaMaoyanPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl(value = "http://m.maoyan.com/[\\w\\W]*")
public class CinemaMaoyanModel implements AfterExtractor {

  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl("cityId=(\\d*)")
  private String cityId = "";

  @ExtractByUrl("(?<=cityName=)(.*?)((?=&)|(?=$))")
  private String cityName;

  public static void main(String[] args) {

    String url = "http://m.maoyan.com/cinemas.json?cityId="
      + 1 + "&cityName=北京";

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemaMaoyanPipeline pipeline = applicationContext.getBean(CinemaMaoyanPipeline.class);

    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(1000).setCycleRetryTimes(4)
      , pipeline, CinemaMaoyanModel.class)
      .addUrl(url).thread(1).run();

  }


  @Override
  public String toString() {
    String retVal = context;
    return retVal;
  }

  @Override
  public void afterProcess(Page page) {

  }


  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }
}
