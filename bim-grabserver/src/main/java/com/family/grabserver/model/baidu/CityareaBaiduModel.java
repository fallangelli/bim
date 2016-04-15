package com.family.grabserver.model.baidu;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.baidu.CityareaBaiduPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl(value = "http://m.dianying.baidu.com/info/cinema/nearby[\\w\\W]*")
public class CityareaBaiduModel implements AfterExtractor {


  @ExtractBy("/html/body/regex('(?<=cinemaStr: \\{\"filter\":\\{\"areas\":)(.+?)(?=\\}],)')")
  private String area;

  @ExtractByUrl("c=(\\d*)")
  private String cityId = "";

  @ExtractByUrl("(?<=cityName=)(.*?)((?=&)|(?=$))")
  private String cityName = "";

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CityareaBaiduPipeline pipeline = applicationContext.getBean(CityareaBaiduPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(3),
      pipeline, CityareaBaiduModel.class)
      .addUrl("http://m.dianying.baidu.com/info/cinema/nearby?sfrom=newnuomi&from=webapp&c=132&cityName=重庆市&district=%E5%85%A8%E9%83%A8%E5%95%86%E5%9C%88").thread(1).run();
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
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

  @Override
  public void afterProcess(Page page) {
    area = area.trim();
    area += "}]";
  }

}
