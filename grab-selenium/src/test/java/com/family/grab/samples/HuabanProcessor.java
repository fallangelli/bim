package com.family.grab.samples;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.Spider;
import com.family.grab.downloader.selenium.SeleniumDownloader;
import com.family.grab.pipeline.FilePipeline;
import com.family.grab.processor.PageProcessor;

/**
 * 花瓣网抽取器。<br>
 * 使用Selenium做页面动态渲染。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-26 <br>
 *         Time: 下午4:08 <br>
 */
public class HuabanProcessor implements PageProcessor {

  private Site site;

  public static void main(String[] args) {
    Spider.create(new HuabanProcessor()).thread(5)
      .addPipeline(new FilePipeline("/data/webmagic/test/"))
      .setDownloader(new SeleniumDownloader("/Users/yihua/Downloads/chromedriver"))
      .addUrl("http://huaban.com/")
      .runAsync();
  }

  @Override
  public void process(Page page) {
    page.addTargetRequests(page.getHtml().links().regex("http://huaban\\.com/.*").all());
    if (page.getUrl().toString().contains("pins")) {
      page.putField("img", page.getHtml().xpath("//div[@id='pin_img']/a/img/@src").toString());
    } else {
      page.getResultItems().setSkip(true);
    }
  }

  @Override
  public Site getSite() {
    if (null == site) {
      site = Site.me().setDomain("huaban.com").setSleepTime(0);
    }
    return site;
  }
}
