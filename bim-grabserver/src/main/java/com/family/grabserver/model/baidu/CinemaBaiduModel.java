package com.family.grabserver.model.baidu;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.baidu.CinemaBaiduPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TargetUrl(value = "http://m.dianying.baidu.com/api/portal/loadMoreCinema[\\w\\W]*")
public class CinemaBaiduModel implements AfterExtractor {

  @ExtractBy(value = "/html/body")
  private String context;

  //  @ExtractBy(value = "/html/body/regex('(?<=data-uid=\")(.+?)(?=\">)')", multi = true)
  private List<String> listId = new ArrayList<>();

  //  @ExtractBy(value = "/html/body/a[@class='schedule-info touching border border-bottom']/div/div[1]/div[1]/text()", multi = true)
  private List<String> listName = new ArrayList<>();

  //  @ExtractBy(value = "/html/body/a[@class='schedule-info touching border border-bottom']/div/p/text()", multi = true)
  private List<String> listAddress = new ArrayList<>();

  @ExtractByUrl("c=(\\d*)")
  private String cityId;

  @ExtractByUrl("(?<=cityName=)(.*?)((?=&)|(?=$))")
  private String cityName;

  @ExtractByUrl("areaId=(\\d*)")
  private String areaId;

  @ExtractByUrl("(?<=areaName=)(.*?)((?=&)|(?=$))")
  private String areaName;

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemaBaiduPipeline pipeline = applicationContext.getBean(CinemaBaiduPipeline.class);

    OOSpider.create(Site.me().setSleepTime(1000)
      , pipeline, CinemaBaiduModel.class)
      .addUrl("http://m.dianying.baidu.com/api/portal/loadMoreCinema?c=131&cityName=北京&areaId=2305&areaName=昌平&pageSize=1000&pageNum=0")
      .thread(1).run();
  }

  @Override
  public void afterProcess(Page page) {
    Pattern pattern = Pattern.compile("(?<=data-uid=\"\\\\\")(.+?)(?=\\\\\"\">)");
    Matcher matcherId = pattern.matcher(context);
    while (matcherId.find())
      listId.add(matcherId.group());

    pattern = Pattern.compile("(?<=<div class=\\\"\\\\\\\"name\\\\\\\"\\\" style=\\\"\\\\\\\"max-width:\\\" \\d?\\d\\d%\\\\\"\\=\\\"\\\">)(.+?)(?= </div>)", Pattern.DOTALL);
    Matcher matcherName = pattern.matcher(context);
    while (matcherName.find())
      listName.add(matcherName.group().trim());

    pattern = Pattern.compile("(?<=<p class=\\\"\\\\\\\"cinema-address\\\\\\\"\\\">)(.+?)(?=</p>)");
    Matcher matcherAddress = pattern.matcher(context);
    while (matcherAddress.find())
      listAddress.add(matcherAddress.group().trim());
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

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

}
