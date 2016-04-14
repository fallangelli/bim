package com.family.grabserver.model.Weixin;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.weixin.MovieshowingWeixinPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TargetUrl(value = "http://m.wepiao.com/data/v5/[\\w\\W]*")
public class MovieshowingWeixinModel implements AfterExtractor {
  @ExtractByUrl("cityId=(\\d*)")
  private String cityId;

  @ExtractByUrl("(?<=cityName=)(.*?)((?=&)|(?=$))")
  private String cityName;

  @ExtractBy(value = "/html/body/text()")
  private String context;

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final MovieshowingWeixinPipeline pipeline = applicationContext.getBean(MovieshowingWeixinPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(30),
      pipeline, MovieshowingWeixinModel.class)
      .addUrl("http://m.wepiao.com/data/v5/movies/cities/1915/movies_city_1915.json?cityId=1915&cityName=永康").thread(1).run();
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
    Pattern pattern = Pattern.compile("(?<=\"movies_city_" + cityId + "\",)(.+?)(?=\\);)");
    Matcher matcher = pattern.matcher(context);
    if (matcher.find())
      context = matcher.group();
  }

}
