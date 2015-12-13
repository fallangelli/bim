package com.family.grabserver.model.mtime;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.maoyan.CityareaMtimePipeline;

@TargetUrl(value = "http://m.mtime.cn/Service[\\w\\W]*")
public class CityareaMtimeModel implements AfterExtractor {


  @ExtractBy(value = "/html/body")
  private String context;


  @ExtractByUrl("locationId=(\\d*)[&]?")
  private String cityId = "";

  public static void main(String[] args) {
    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(3),
      new CityareaMtimePipeline(), CityareaMtimeModel.class)
      .addUrl("http://m.mtime.cn/Service/callback.mi/Showtime/BaseCityData.api?locationId=480").thread(1).run();
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
  public void afterProcess(Page page) {
    context = context.replace("<body>", "");
    context = context.replace("</body>", "");
    context = context.replace("\n", "");
    context = context.replace("<strong>", "");
    context = context.replace("</strong>", "");
  }

}
