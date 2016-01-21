package com.family.grabserver.model.maoyan;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;

@TargetUrl(value = "http://m.maoyan.com/[\\w\\W]*")
public class CinemaMaoyanModel implements AfterExtractor {

  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl("cityId=(\\d*)?[&]")
  private String cityId = "";

  public static void main(String[] args) {

    String url = "http://m.maoyan.com/cinemas.json?cityId="
      + 362 + "&cityName=测试";

    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(1000).setCycleRetryTimes(4)
      , new ConsolePageModelPipeline(), CinemaMaoyanModel.class)
      .addUrl(url).thread(1).run();

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

  @Override
  public String toString() {
    String retVal = context;
    return retVal;
  }

  @Override
  public void afterProcess(Page page) {

  }

}
