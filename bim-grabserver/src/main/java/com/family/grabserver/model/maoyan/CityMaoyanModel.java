package com.family.grabserver.model.maoyan;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grab.pipeline.ConsolePipeline;

@TargetUrl(value = "http://m.maoyan.com/changecity.json")
public class CityMaoyanModel implements AfterExtractor {

  @ExtractBy(value = "/html/body/text()")
  private String context;

  public static void main(String[] args) {
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(1000)
      , new ConsolePageModelPipeline(), CityMaoyanModel.class)
      .addPipeline(new ConsolePipeline())
      .addUrl("http://m.maoyan.com/changecity.json").thread(5).run();
//        OOSpider.create(Site.me().setSleepTime(1000)
//                , new ConsolePageModelPipeline(), com.family.grabserver.model.DoubanComing.class)
//                .addUrl("http://movie.douban.com/coming")
//                .addPipeline(new JsonFilePipeline("D:\\grab\\"))
//                .addPipeline(new ConsolePipeline()).thread(5).run();
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
