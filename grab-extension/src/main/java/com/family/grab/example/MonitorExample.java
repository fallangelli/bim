package com.family.grab.example;

import com.family.grab.Spider;
import com.family.grab.monitor.SpiderMonitor;
import com.family.grab.processor.example.GithubRepoPageProcessor;
import com.family.grab.processor.example.OschinaBlogPageProcessor;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public class MonitorExample {

  public static void main(String[] args) throws Exception {

    Spider oschinaSpider = Spider.create(new OschinaBlogPageProcessor())
      .addUrl("http://my.oschina.net/flashsword/blog");
    Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
      .addUrl("https://github.com/code4craft");

    SpiderMonitor.instance().register(oschinaSpider);
    SpiderMonitor.instance().register(githubSpider);
    oschinaSpider.start();
    githubSpider.start();
  }
}
