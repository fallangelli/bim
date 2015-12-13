package com.family.grab.processor;

import com.family.grab.Page;
import com.family.grab.ResultItems;
import com.family.grab.Site;
import com.family.grab.Task;
import com.family.grab.downloader.MockGithubDownloader;
import com.family.grab.model.OOSpider;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grab.pipeline.Pipeline;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author code4crafter@gmail.com
 */
public class GithubRepoProcessor implements PageProcessor {
  @Override
  public void process(Page page) {
    page.putField("star", page.getHtml().xpath("//ul[@class='pagehead-actions']/li[2]//a[@class='social-count js-social-count']/text()").toString());
    page.putField("fork", page.getHtml().xpath("//ul[@class='pagehead-actions']/li[3]//a[@class='social-count']/text()").toString());
  }

  @Override
  public Site getSite() {
    return Site.me().addStartUrl("https://github.com/code4craft/grab");
  }

  @Test
  public void testRunCycleTriedTimes() {
    OOSpider.create(Site.me().setTimeOut(30000).setSleepTime(500).setCycleRetryTimes(5).setRetrySleepTime(3000),
      new PageModelPipeline() {
        @Override
        public void process(Object o, Task task) {

        }
      })
      .addUrl("http://localhost/404")
      .thread(1).run();
  }

  @Test
  public void test() {
    OOSpider.create(new GithubRepoProcessor()).addPipeline(new Pipeline() {
      @Override
      public void process(ResultItems resultItems, Task task) {
        Assert.assertEquals("78", ((String) resultItems.get("star")).trim());
        Assert.assertEquals("65", ((String) resultItems.get("fork")).trim());
      }
    }).setDownloader(new MockGithubDownloader()).test("https://github.com/code4craft/grab");
  }

}
