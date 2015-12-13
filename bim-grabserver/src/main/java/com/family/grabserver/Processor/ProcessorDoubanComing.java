package com.family.grabserver.Processor;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.Spider;
import com.family.grab.pipeline.ConsolePipeline;
import com.family.grab.pipeline.JsonFilePipeline;
import com.family.grab.processor.PageProcessor;


public class ProcessorDoubanComing implements PageProcessor {

  private Site site = Site.me().setCycleRetryTimes(3).setSleepTime(1000);

  public static void main(String[] args) {

    Spider.create(new ProcessorDoubanComing())
      .addUrl("http://movie.douban.com/coming")
      .addPipeline(new JsonFilePipeline("D:\\grab\\"))
      .addPipeline(new ConsolePipeline())
      .thread(5)
      .run();
  }

  @Override
  public void process(Page page) {
    page.putField("name", page.getHtml().xpath("//*[@id=\"content\"]/h1/span[1]/text()").toString());
//        page.putField("links", page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex("http://movie\\.douban\\.com\\/subject\\/[0-9]+\\/").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

    page.addTargetRequests(page.getHtml().xpath("//*[@id=\"content\"]/div/div[1]/table/tbody/").links().regex("http://movie\\.douban\\.com\\/subject\\/[0-9]+\\/").all());
//        page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex("http://movie\\.douban\\.com\\/subject\\/[0-9]+\\/").all());
    //page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
  }

  @Override
  public Site getSite() {
    return site;
  }
}
