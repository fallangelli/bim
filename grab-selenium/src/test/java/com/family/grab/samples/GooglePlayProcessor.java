package com.family.grab.samples;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.Spider;
import com.family.grab.downloader.selenium.SeleniumDownloader;
import com.family.grab.pipeline.FilePipeline;
import com.family.grab.processor.PageProcessor;

/**
 * Using Selenium with PhantomJS to fetch web-page with JS<br>
 *
 * @author bob.li.0718@gmail.com <br>
 *         Date: 15-7-11 <br>
 */
public class GooglePlayProcessor implements PageProcessor {

  private Site site;

  public static void main(String[] args) {
    Spider.create(new GooglePlayProcessor())
      .thread(5)
      .addPipeline(
        new FilePipeline(
          "/Users/Bingo/Documents/workspace/webmagic/webmagic-selenium/data/"))
      .setDownloader(new SeleniumDownloader())
      .addUrl("https://play.google.com/store/apps/details?id=com.tencent.mm")
      .runAsync();
  }

  @Override
  public void process(Page page) {

    page.putField("whole-html", page.getHtml().toString());

  }

  @Override
  public Site getSite() {
    if (null == site) {
      site = Site.me().setDomain("play.google.com").setSleepTime(300);
    }
    return site;
  }
}
