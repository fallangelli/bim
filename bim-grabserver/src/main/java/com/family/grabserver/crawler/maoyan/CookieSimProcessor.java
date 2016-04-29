package com.family.grabserver.crawler.maoyan;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/22.
 */
public class CookieSimProcessor {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  private CookieStore cookieStore;

  private CookieSimProcessor() {

  }

  public CookieSimProcessor(Integer cityId) {
    this.refreshCookies(cityId);
  }

  public static void main(String[] args) {
    CookieSimProcessor processor = new CookieSimProcessor(66);
    System.out.println("cookie store:" + processor.getCookieStore().getCookies());
  }

  public void refreshCookies(Integer cityId) {
    cookieStore = new BasicCookieStore();
    BasicClientCookie cookie = new BasicClientCookie("ci", cityId.toString());
    cookie.setDomain(".maoyan.com");
    cookie.setPath("/");
    cookieStore.addCookie(cookie);

    // 使用cookieStore方式
    CloseableHttpClient client = HttpClients.custom()
      .setDefaultCookieStore(cookieStore).build();
    HttpGet httpGet = new HttpGet("http://m.maoyan.com/#type=cinemas");

    try {
      // 执行get请求
      client.execute(httpGet);

      boolean cookeiExists = false;
      int retryTime = 0;
      for (Cookie tmpCookie : cookieStore.getCookies()) {
        if (tmpCookie.getName().compareToIgnoreCase("JSESSIONID") == 0)
          cookeiExists = true;
        break;
      }
      while (++retryTime <= 10 && !cookeiExists) {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        logger.warn("城市" + cityId + " cookie设置错误，重试第 " + retryTime + " 次");
        client.execute(httpGet);
        for (Cookie tmpCookie : cookieStore.getCookies()) {
          if (tmpCookie.getName().compareToIgnoreCase("JSESSIONID") == 0)
            cookeiExists = true;
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public CookieStore getCookieStore() {
    return cookieStore;

  }

  public void setCookieStore(CookieStore cookieStore) {
    this.cookieStore = cookieStore;
  }
}
