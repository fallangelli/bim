package com.family.grabserver.model.Weixin;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.weixin.CinemaWeixinPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TargetUrl(value = "http://m.wepiao.com/data/v5/[\\w\\W]*")
public class CinemaWeixinModel implements AfterExtractor {
  @ExtractByUrl("cityId=(\\d*)")
  private String cityId;

  @ExtractByUrl("(?<=cityName=)(.*?)((?=&)|(?=$))")
  private String cityName;

  @ExtractBy(value = "/html/body/text()")
  private String context;

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemaWeixinPipeline pipeline = applicationContext.getBean(CinemaWeixinPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(30),
      pipeline, CinemaWeixinModel.class)
      .addUrl("http://m.wepiao.com/data/v5/cinemas/cities/11/cinemas_city_11.json?cityId=11&cityName=天津").thread(1).run();
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

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public void afterProcess(Page page) {
    Pattern pattern = Pattern.compile("(?<=\"cinemas_city_" + cityId + "\",)(.+?)(?=\\);)");
    Matcher matcher = pattern.matcher(context);
    if (matcher.find())
      context = matcher.group();
  }

}
