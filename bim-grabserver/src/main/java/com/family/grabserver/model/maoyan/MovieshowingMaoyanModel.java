package com.family.grabserver.model.maoyan;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.maoyan.MovieshowingMaoyanPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl(value = "http://m.maoyan.com/cinemas/[\\w\\W]*")
public class MovieshowingMaoyanModel implements AfterExtractor {

  @ExtractBy(value = "/html/body")
  private String context;

  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final MovieshowingMaoyanPipeline pipeline = applicationContext.getBean(MovieshowingMaoyanPipeline.class);

    OOSpider.create(Site.me().setTimeOut(60000)
        .addCookie("JSESSIONID", "1mucuojy5jctr1j3ed0wfy231")
        .addCookie("ci", "1")
        .addCookie("iuuid", "1AD1CD05C594CDBD4106AAFCC3F165C5B8072B93B1D35C8E71EE3E246E009512"),
      pipeline, MovieshowingMaoyanModel.class)
      .addUrl("http://m.maoyan.com/cinemas/list.json?movieid=246970").thread(1).run();
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
