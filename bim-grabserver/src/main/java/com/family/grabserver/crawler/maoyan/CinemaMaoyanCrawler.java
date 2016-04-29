package com.family.grabserver.crawler.maoyan;

import com.family.grabserver.entity.bim_grab.CityMaoyan;
import com.family.grabserver.model.maoyan.CinemaMaoyanModel;
import com.family.grabserver.pipeline.maoyan.CinemaMaoyanPipeline;
import com.family.grabserver.service.maoyan.CityMaoyanService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class CinemaMaoyanCrawler {
  @Autowired
  private CinemaMaoyanPipeline cinemaMaoyanPipeline;

  @Autowired
  private CityMaoyanService cityService;
  @Autowired
  private CinemaMaoyanPipeline pipeline;


  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemaMaoyanCrawler jsonCrawler = applicationContext.getBean(CinemaMaoyanCrawler.class);
    jsonCrawler.crawl();
  }

  public void crawl() {
    ExecutorService pool = Executors.newFixedThreadPool(10);

    List<CityMaoyan> allCity = cityService.selectAll();

    logger.info("开始抓取 猫眼 影院信息");

    for (CityMaoyan city : allCity) {
      String url = "http://m.maoyan.com/cinemas.json?cityId="
        + city.getId() + "&cityName=" + city.getName();

      CinemaMaoyanCrawler.CinemaThread th = new CinemaThread(city.getId(), city.getName(), url);
      pool.execute(th);
    }
    pool.shutdown();
    try {//等待直到所有任务完成
      pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    logger.info("完成抓取 猫眼 影院信息");

  }


  public class CinemaThread implements Runnable {
    Integer cityId;
    String cityName;
    String url;

    public CinemaThread(Integer cityId, String cityName, String url) {
      this.cityId = cityId;
      this.cityName = cityName;
      this.url = url;
    }

    @Override
    public void run() {
      DefaultHttpClient client = new DefaultHttpClient(new PoolingClientConnectionManager());
      CookieSimProcessor cookieSimer = new CookieSimProcessor(cityId);
      client.setCookieStore(cookieSimer.getCookieStore());
      HttpGet get = new HttpGet(url);
      Integer totalRetryTimes = 10;
      Integer retriedTimes = 0;

      try {
        HttpResponse response = client.execute(get);
        String context = paseResponse(response);

        while (retriedTimes++ < totalRetryTimes && cityId != 1
          && (context == null || context.length() == 0 || context.contains("密云县"))) {
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          logger.warn(url + ":目标城市错误，重试第 " + retriedTimes + " 次");
          client = new DefaultHttpClient(new PoolingClientConnectionManager());
          cookieSimer = new CookieSimProcessor(cityId);
          client.setCookieStore(cookieSimer.getCookieStore());
          get = new HttpGet(url);
          response = client.execute(get);
          context = paseResponse(response);
        }

        if (retriedTimes >= totalRetryTimes - 1) {
          logger.error("目标城市错误 : " + url);
          return;
        }

        CinemaMaoyanModel model = new CinemaMaoyanModel();
        model.setCityId(cityId.toString());
        model.setCityName(cityName);
        model.setContext(context);

        pipeline.process(model, null);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    public Integer getCityId() {
      return cityId;
    }

    public void setCityId(Integer cityId) {
      this.cityId = cityId;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }


    private String paseResponse(HttpResponse response) {
      HttpEntity entity = response.getEntity();

      BufferedReader br = null;
      try {
        br = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));

        String tempbf;
        StringBuffer html = new StringBuffer(100);
        while ((tempbf = br.readLine()) != null) {
          html.append(tempbf + "\n");
        }
        return html.toString();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
  }


}
