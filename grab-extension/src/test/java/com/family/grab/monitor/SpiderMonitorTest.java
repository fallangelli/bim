package com.family.grab.monitor;

import com.family.grab.Spider;
import com.family.grab.processor.example.GithubRepoPageProcessor;
import com.family.grab.processor.example.OschinaBlogPageProcessor;
import org.junit.Test;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public class SpiderMonitorTest {

  @Test
  public void testInherit() throws Exception {
    SpiderMonitor spiderMonitor = new SpiderMonitor() {
      @Override
      protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
        return new CustomSpiderStatus(spider, monitorSpiderListener);
      }
    };

    Spider oschinaSpider = Spider.create(new OschinaBlogPageProcessor())
      .addUrl("http://my.oschina.net/flashsword/blog").thread(2);
    Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
      .addUrl("https://github.com/code4craft");

    spiderMonitor.register(oschinaSpider, githubSpider);

  }
}
