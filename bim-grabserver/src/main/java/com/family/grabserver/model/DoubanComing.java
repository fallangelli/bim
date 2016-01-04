package com.family.grabserver.model;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.ConsolePageModelPipeline;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.HelpUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grab.pipeline.ConsolePipeline;
import com.family.grab.pipeline.JsonFilePipeline;
import org.apache.commons.codec.digest.DigestUtils;

@TargetUrl(value = "http://movie.douban.com/subject/[0-9]+/", sourceRegion = "//*[@id=\"content\"]/div/div[1]/table/tbody/")
@HelpUrl("https://movie.douban.com/[0-9]+")
//@TargetUrl("http://movie\\.douban\\.com\\/subject\\/[0-9]+\\/")
//@HelpUrl(value = "http://movie.douban.com/coming", sourceRegion = "//*[@id=\"content\"]/div/div[1]/table/tbody/")
public class DoubanComing implements AfterExtractor {

  @ExtractBy(value = "//*[@id=\"content\"]/h1/span[1]/text()", notNull = true)
  private String title;

  @ExtractBy(value = "//*[@id=\"info\"]/span[1]/span[2]")
  private String director;

  @ExtractBy(value = "//*[@id=\"info\"]/span[3]/span[2]")
  private String actor;

  @ExtractBy(value = "//*[@id=\"info\"]/span[5]/text()")
  private String type;

//    @ExtractBy(value = "//*[@id=\"info\"]/text()[3]")
//    private String language;
//
//    @ExtractBy(value = "//*[@id=\"info\"]/span[10]/text()")
//    private String release_date;

  private String source = "movie.douban.com";

  @ExtractByUrl
  private String url = "";
  private String urlMd5 = "";

  public static void main(String[] args) {
    OOSpider.create(Site.me().setTimeOut(60000).setSleepTime(1000)
      , new ConsolePageModelPipeline(), DoubanComing.class)
      .addPipeline(new JsonFilePipeline("D:\\grab\\"))
      .addPipeline(new ConsolePipeline())
      .addUrl("http://movie.douban.com/coming").thread(5).run();
//        OOSpider.create(Site.me().setSleepTime(1000)
//                , new ConsolePageModelPipeline(), com.family.grabserver.model.DoubanComing.class)
//                .addUrl("http://movie.douban.com/coming")
//                .addPipeline(new JsonFilePipeline("D:\\grab\\"))
//                .addPipeline(new ConsolePipeline()).thread(5).run();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
    this.urlMd5 = DigestUtils.md5Hex(url);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    String retVal = title + "|导演:" + director + "|主演:" + actor + "|类型:" + type;
//                "|语言:" + language + "|上映日期:" + release_date;
    return retVal;
  }

  @Override
  public void afterProcess(Page page) {

  }

}
