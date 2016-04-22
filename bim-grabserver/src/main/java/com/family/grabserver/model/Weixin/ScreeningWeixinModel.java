package com.family.grabserver.model.Weixin;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.weixin.ScreeningWeixinPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TargetUrl(value = "http://m.wepiao.com/data/v5/[\\w\\W]*")
public class ScreeningWeixinModel implements AfterExtractor {

  @ExtractByUrl("cityId=(\\d*)")
  private String cityId;

  @ExtractByUrl("cinemaId=(\\d*)")
  private String cinemaId;


  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl
  private String url;

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final ScreeningWeixinPipeline pipeline = applicationContext.getBean(ScreeningWeixinPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(30),
      pipeline, ScreeningWeixinModel.class)
      .addUrl("http://m.wepiao.com/data/v5/cinemas/cities/155/sched_city_cinema_155_1012755.json?cityId=155&cinemaId=1012755").thread(1).run();
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(String cinemaId) {
    this.cinemaId = cinemaId;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public void afterProcess(Page page) {
    Pattern pattern = Pattern.compile("(?<=\"sched_city_cinema_" + cityId + "_" + cinemaId + "\",)(.+?)(?=\\);)");
    Matcher matcher = pattern.matcher(context);
    if (matcher.find())
      context = matcher.group();
    context = context.trim();
  }

}
