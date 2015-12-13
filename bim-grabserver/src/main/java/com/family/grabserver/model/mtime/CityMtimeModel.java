package com.family.grabserver.model.mtime;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.TargetUrl;

@TargetUrl(value = "http://m.mtime.cn/Service/callback.mi/Showtime/HotCitiesByCinema.api")
public class CityMtimeModel implements AfterExtractor {


  @ExtractBy(value = "/html/body/text()")
  private String context;

  public static void main(String[] args) {
    OOSpider.create(Site.me().setSleepTime(1000),
      new ConsolePageModelPipeline(), CityMtimeModel.class)
      .addUrl("http://m.mtime.cn/Service/callback.mi/Showtime/HotCitiesByCinema.api").thread(1).run();
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public void afterProcess(Page page) {

  }

}
