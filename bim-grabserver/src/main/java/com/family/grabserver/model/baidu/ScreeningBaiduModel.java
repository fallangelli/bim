package com.family.grabserver.model.baidu;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grab.pipeline.ConsolePipeline;

@TargetUrl("http://m.dianying.baidu.com/info/cinema/detail[\\w\\W]*")
public class ScreeningBaiduModel implements AfterExtractor {


  @ExtractBy(value = "/html/body/script[2]")
  private String context;


  @ExtractByUrl("cinemaId=(\\d*)")
  private String cinemaId = "";

  public static void main(String[] args) {
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(1000)
      , new ConsolePageModelPipeline(), ScreeningBaiduModel.class)
      .addPipeline(new ConsolePipeline())
      .addUrl("http://m.dianying.baidu.com/info/cinema/detail?cinemaId=148").thread(1).run();
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

  @Override
  public void afterProcess(Page page) {
    String tmp = context;
    context = tmp.substring(tmp.indexOf("_MOVIE.data =") + 13, tmp.lastIndexOf("};") + 1).trim();
    context.replace("\\n", "");
  }

}
