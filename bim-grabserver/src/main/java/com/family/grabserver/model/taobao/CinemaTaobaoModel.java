package com.family.grabserver.model.taobao;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;

public class CinemaTaobaoModel implements AfterExtractor {


  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl("cityId=(\\d*)")
  private String cityId = "";

  public static void main(String[] args) {
    OOSpider.create(Site.me().setSleepTime(1000)
        .addCookie("_m_h5_tk", "9bd87dcd95d096a44935e325c2dd3dd7_1451029178429")
        .addCookie("isg", "E560BB13F22E94262498D9AE70E97B60")
        .addCookie("_m_h5_tk_enc", "6c05a2503663e622efb9881612b568d5"),
      new ConsolePageModelPipeline(), CinemaTaobaoModel.class)
      .addUrl("http://api.m.taobao.com/h5/mtop.film.mtopregionapi.getallregion/4.0/?v=4.0&api=mtop.film.MtopRegionAPI.getAllRegion&appKey=12574478&t=1451023779495&callback=mtopjsonp6&type=jsonp&sign=6ac0e3c1bb49e647fa64c1a07f0f2dc9&data=%7B%22platform%22%3A%228%22%7D").thread(1).run();
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public void afterProcess(Page page) {
    if (context.toUpperCase().indexOf("SUCCESS") < 0)
      System.out.println(context);
  }

}
