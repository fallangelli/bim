package com.family.grabserver.model.taobao;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;

import java.util.List;

public class CityTaobaoModel implements AfterExtractor {

  @ExtractBy(value = "//*[@id=\"A\"]/ul/li[4]/a/text()", multi = true)
  private List<String> names;


  @ExtractBy(value = "/html/body")
  private String name;

  public static void main(String[] args) {
    OOSpider.create(Site.me().setSleepTime(1000),
      new ConsolePageModelPipeline(), CityTaobaoModel.class)
      .addUrl("http://h5.m.taobao.com/app/movie/pages/index/index.html").thread(1).run();
  }

  public List<String> getNames() {
    return names;
  }

  public void setNames(List<String> names) {
    this.names = names;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void afterProcess(Page page) {

  }

}
