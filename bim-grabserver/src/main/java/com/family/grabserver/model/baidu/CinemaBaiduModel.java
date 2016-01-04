package com.family.grabserver.model.baidu;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;

import java.util.List;

@TargetUrl(value = "http://m.dianying.baidu.com/api/portal/loadMoreCinema[\\w\\W]*")
public class CinemaBaiduModel implements AfterExtractor {

  @ExtractBy(value = "/html/body/a[@class='schedule-info touching border border-bottom']/div/@data-uid", multi = true)
  private List<String> listId;

  @ExtractBy(value = "/html/body/a[@class='schedule-info touching border border-bottom']/div/div[1]/div[1]/text()", multi = true)
  private List<String> listName;

  @ExtractBy(value = "/html/body/a[@class='schedule-info touching border border-bottom']/div/p/text()", multi = true)
  private List<String> listAddress;

  @ExtractByUrl("c=(\\d*)[&]?")
  private String cityId;

  @ExtractByUrl("areaId=(\\d*)[&]?")
  private String areaId;

  @ExtractByUrl("areaName=([\\w\\W]*?)[&]")
  private String areaName;

  public static void main(String[] args) {
    OOSpider.create(Site.me().setSleepTime(1000)
      , new ConsolePageModelPipeline(), CinemaBaiduModel.class)
      .addUrl("http://m.dianying.baidu.com/api/portal/loadMoreCinema?c=131&areaId=2305&areaName=昌平&pageSize=1000&pageNum=0")
      .thread(1).run();
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

  public List<String> getListAddress() {
    return listAddress;
  }

  public void setListAddress(List<String> listAddress) {
    this.listAddress = listAddress;
  }

  public String getAreaId() {
    return areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  @Override
  public void afterProcess(Page page) {

  }

}
