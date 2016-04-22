package com.family.grab.downloader.selenium;

import com.family.grab.Page;
import com.family.grab.Request;
import com.family.grab.Site;
import com.family.grab.Task;
import com.family.grab.downloader.Downloader;
import com.family.grab.selector.Html;
import com.family.grab.selector.PlainText;
import com.family.grab.utils.UrlUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

public class SeleniumDownloader implements Downloader, Closeable {

  private static final String DRIVER_PHANTOMJS = "phantomjs";
  private volatile WebDriverPool webDriverPool;
  private Logger logger = Logger.getLogger(getClass());
  private int sleepTime = 0;
  private int poolSize = 1;

  /**
   * 新建
   *
   * @param chromeDriverPath chromeDriverPath
   */
  public SeleniumDownloader(String chromeDriverPath) {
    System.getProperties().setProperty("webdriver.chrome.driver",
      chromeDriverPath);
  }

  /**
   * Constructor without any filed. Construct PhantomJS browser
   *
   * @author bob.li.0718@gmail.com
   */
  public SeleniumDownloader() {
    // System.setProperty("phantomjs.binary.path",
    // "/Users/Bingo/Downloads/phantomjs-1.9.7-macosx/bin/phantomjs");
  }

  /**
   * set sleep time to wait until load success
   *
   * @param sleepTime sleepTime
   * @return this
   */
  public SeleniumDownloader setSleepTime(int sleepTime) {
    this.sleepTime = sleepTime;
    return this;
  }

  @Override
  public Page download(Request request, Task task) {
    checkInit();
    WebDriver webDriver;
    try {
      webDriver = webDriverPool.get();
    } catch (InterruptedException e) {
      logger.warn("interrupted", e);
      return null;
    }
//    logger.info("downloading page " + request.getUrl());
    webDriver.get(request.getUrl());
    try {
      Thread.sleep(sleepTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    WebDriver.Options manage = webDriver.manage();
    Site site = task.getSite();
    if (site.getCookies() != null) {
      for (Map.Entry<String, String> cookieEntry : site.getCookies()
        .entrySet()) {
        Cookie cookie = new Cookie(cookieEntry.getKey(),
          cookieEntry.getValue());
        manage.addCookie(cookie);
      }
    }

		/*
     * TODO You can add mouse event or other processes
		 *
		 * @author: bob.li.0718@gmail.com
		 */

    WebElement webElement = webDriver.findElement(By.xpath("/html"));
    String content = webElement.getAttribute("outerHTML");
    Page page = new Page();
    page.setRawText(content);
    page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content,
      request.getUrl())));
    page.setUrl(new PlainText(request.getUrl()));
    page.setRequest(request);
    webDriverPool.returnToPool(webDriver);
    return page;
  }

  private void checkInit() {
    if (webDriverPool == null) {
      synchronized (this) {
        webDriverPool = new WebDriverPool(poolSize);
      }
    }
  }

  @Override
  public void setThread(int thread) {
    this.poolSize = thread;
  }

  @Override
  public void close() throws IOException {
    webDriverPool.closeAll();
  }
}
