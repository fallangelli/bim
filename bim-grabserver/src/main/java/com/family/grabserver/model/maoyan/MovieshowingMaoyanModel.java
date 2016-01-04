package com.family.grabserver.model.maoyan;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.TargetUrl;

@TargetUrl(value = "http://m.maoyan.com/cinemas/[\\w\\W]*")
public class MovieshowingMaoyanModel implements AfterExtractor {

  @ExtractBy(value = "/html/body")
  private String context;

  public static void main(String[] args) {
    OOSpider.create(Site.me().setTimeOut(60000)
      , new ConsolePageModelPipeline(), MovieshowingMaoyanModel.class)
      .addUrl("http://m.maoyan.com/cinemas/list.json?movieid=117").thread(1).run();
  }

  public String getContext() {

    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public void afterProcess(Page page) {
    context = context.replace("<body>", "");
    context = context.replace("</body>", "");
    context = context.replace("\n", "");

  }

}
