package com.family.grabserver.crawler.maoyan;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/22.
 */
public class CookieSimProcessor {
  private CookieStore cookieStore;

  private CookieSimProcessor() {

  }

  public CookieSimProcessor(Integer cityId) {
    this.refreshCookies(cityId);
  }

  public static void main(String[] args) {
    CookieSimProcessor processor = new CookieSimProcessor(1);
    System.out.println("cookie store:" + processor.getCookieStore().getCookies());


  }

  public void refreshCookies(Integer cityId) {
    cookieStore = new BasicCookieStore();
    BasicClientCookie cookie = new BasicClientCookie("ci", cityId.toString());

    cookieStore.addCookie(cookie);
    // 使用cookieStore方式
    CloseableHttpClient client = HttpClients.custom()
      .setDefaultCookieStore(cookieStore).build();
    HttpGet httpGet = new HttpGet("http://m.maoyan.com/");
    System.out.println("request line:" + httpGet.getRequestLine());
    try {
      // 执行get请求
      HttpResponse httpResponse = client.execute(httpGet);
      System.out.println("cookie store:" + cookieStore.getCookies());

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 关闭流并释放资源
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public CookieStore getCookieStore() {
    return cookieStore;

  }

  public void setCookieStore(CookieStore cookieStore) {
    this.cookieStore = cookieStore;
  }
}
