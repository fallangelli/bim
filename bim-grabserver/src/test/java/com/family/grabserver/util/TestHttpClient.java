
package com.family.grabserver.util;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestHttpClient {

  // 创建CookieStore实例
  static CookieStore cookieStore = null;
  static HttpClientContext context = null;
  String loginUrl = "http://m.dianying.baidu.com/city/choose?sfrom=newnuomi&from=webapp";
  String testUrl = "http://api.m.taobao.com/h5/mtop.film.mtopscheduleapi.getschedulesbydays/4.0/?v=4.0&api=mtop.film.MtopScheduleAPI.getSchedulesByDays&appKey=12574478&t=1451373436671&callback=mtopjsonp15&type=jsonp&sign=379ff88ab65128f1e607a6cababac707&data=%7B%22platform%22%3A%228%22%2C%22asac%22%3A%22D679AU6J95PHQT67G0B5%22%2C%22days%22%3A50%2C%22cinemaid%22%3A%2225196%22%2C%22showid%22%3A141207%7D";
  String loginErrorUrl = "http://api.m.taobao.com/h5/mtop.film.mtopcinemaapi.getmixupcinemas/4.0/?v=4.0&api=mtop.film.MtopCinemaAPI.getMixupCinemas&appKey=12574478&t=1451035935583&callback=mtopjsonp1&type=jsonp&sign=d1291bb1491306a7b8ad57603655d3be&data=%7B%22platform%22%3A%228%22%2C%22asac%22%3A%22D679AU6J95PHQT67G0B5%22%2C%22longitude%22%3A0%2C%22latitude%22%3A0%2C%22supportTypeCode%22%3A3%2C%22cityCode%22%3A310100%7D";

  public static void printResponse(HttpResponse httpResponse)
    throws ParseException, IOException {
    // 获取响应消息实体
    HttpEntity entity = httpResponse.getEntity();
    // 响应状态
    System.out.println("status:" + httpResponse.getStatusLine());
    System.out.println("headers:");
    HeaderIterator iterator = httpResponse.headerIterator();
    while (iterator.hasNext()) {
      System.out.println("\t" + iterator.next());
    }
    // 判断响应实体是否为空
    if (entity != null) {
      String responseString = EntityUtils.toString(entity);
      System.out.println("response length:" + responseString.length());
      System.out.println("response content:"
        + responseString.replace("\r\n", ""));
    }
  }

  public static void setContext() {
    System.out.println("----setContext");
    context = HttpClientContext.create();
    Registry<CookieSpecProvider> registry = RegistryBuilder
      .<CookieSpecProvider>create()
      .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
      .register(CookieSpecs.BROWSER_COMPATIBILITY,
        new BrowserCompatSpecFactory()).build();
    context.setCookieSpecRegistry(registry);
    context.setCookieStore(cookieStore);
  }

  public static void setCookieStore(HttpResponse httpResponse) {
    System.out.println("----setCookieStore");
    cookieStore = new BasicCookieStore();
    // JSESSIONID
    String setCookie = httpResponse.getFirstHeader("Set-Cookie")
      .getValue();
    String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
      setCookie.indexOf(";"));
    System.out.println("JSESSIONID:" + JSESSIONID);
    // 新建一个Cookie
    BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
      JSESSIONID);
    cookie.setVersion(0);
    cookie.setDomain("127.0.0.1");
    cookie.setPath("/CwlProClient");
    // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
    // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
    // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
    // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
    cookieStore.addCookie(cookie);
  }

  public static List<NameValuePair> getParam(Map parameterMap) {
    List<NameValuePair> param = new ArrayList<NameValuePair>();
    Iterator it = parameterMap.entrySet().iterator();
    while (it.hasNext()) {
      Entry parmEntry = (Entry) it.next();
      param.add(new BasicNameValuePair((String) parmEntry.getKey(),
        (String) parmEntry.getValue()));
    }
    return param;
  }

  @Test
  public void testLogin() throws Exception {
    System.out.println("----testLogin");

    // // 创建HttpClientBuilder
    // HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    // // HttpClient
    // CloseableHttpClient client = httpClientBuilder.build();
    // 直接创建client
    CloseableHttpClient client = HttpClients.createDefault();

    try {

      // 执行get请求
      HttpGet httpGet2 = new HttpGet(loginUrl);
      System.out.println("request line:" + httpGet2.getRequestLine());
      HttpResponse httpResponse3 = client.execute(httpGet2);
      printResponse(httpResponse3);

      // 执行get请求
      HttpGet httpGet = new HttpGet(loginUrl);
      System.out.println("request line:" + httpGet.getRequestLine());
      HttpResponse httpResponse1 = client.execute(httpGet);
      printResponse(httpResponse1);

      // 执行get请求
      HttpGet httpGet1 = new HttpGet(loginUrl);
      System.out.println("request line:" + httpGet1.getRequestLine());
      HttpResponse httpResponse2 = client.execute(httpGet1);
      printResponse(httpResponse2);


      HttpResponse httpResponse4 = client.execute(httpGet2);
      printResponse(httpResponse4);


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

  @Test
  public void testContext() throws Exception {
    System.out.println("----testContext");
    // 使用context方式
    CloseableHttpClient client = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(testUrl);
    System.out.println("request line:" + httpGet.getRequestLine());
    try {
      // 执行get请求
      HttpResponse httpResponse = client.execute(httpGet, context);
      System.out.println("context cookies:"
        + context.getCookieStore().getCookies());
      printResponse(httpResponse);
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

  @Test
  public void testCookieStore() throws Exception {
    System.out.println("----testCookieStore");
    // 使用cookieStore方式
    CloseableHttpClient client = HttpClients.custom()
      .setDefaultCookieStore(cookieStore).build();
    HttpGet httpGet = new HttpGet(testUrl);
    System.out.println("request line:" + httpGet.getRequestLine());
    try {
      // 执行get请求
      HttpResponse httpResponse = client.execute(httpGet);
      System.out.println("cookie store:" + cookieStore.getCookies());
      printResponse(httpResponse);
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
}
