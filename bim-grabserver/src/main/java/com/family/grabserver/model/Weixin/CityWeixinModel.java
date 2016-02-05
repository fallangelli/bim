package com.family.grabserver.model.Weixin;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.weixin.CityWeixinPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl("http://m.wepiao.com/data/v5/city.json")
public class CityWeixinModel implements AfterExtractor {

  @ExtractBy(value = "(?<=\"movies_city_\",)(.+?)(?=\\);)", type = ExtractBy.Type.Regex)
  private String context;

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CityWeixinPipeline pipeline = applicationContext.getBean(CityWeixinPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000).setCycleRetryTimes(30),
      pipeline, CityWeixinModel.class)
      .addUrl("http://m.wepiao.com/data/v5/city.json").thread(1).run();

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
