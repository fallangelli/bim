package com.family.grabserver.model.baidu;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grab.pipeline.ConsolePipeline;

import java.util.List;

@TargetUrl("http://m.dianying.baidu.com/city/choose")
public class CityBaiduModel implements AfterExtractor {

  @ExtractBy(value = "//a[@class='city city-item']/@data-citycode", multi = true, notNull = true)
  private List<String> listId;

  @ExtractBy(value = "//a[@class='city city-item']/text()", multi = true)
  private List<String> listName;

  public static void main(String[] args) {
    OOSpider.create(Site.me().setTimeOut(30000).setSleepTime(1000)
      , new ConsolePageModelPipeline(), CityBaiduModel.class)
      .addPipeline(new ConsolePipeline())
      .addUrl("http://m.dianying.baidu.com/city/choose").thread(1).run();
  }

  public List<String> getListId() {
    return listId;
  }

  public void setListId(List<String> listId) {
    this.listId = listId;
  }

  public List<String> getListName() {
    return listName;
  }

  public void setListName(List<String> listName) {
    this.listName = listName;
  }

  @Override
  public void afterProcess(Page page) {

  }

}
