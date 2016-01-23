package com.family.grabserver.pipeline.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.ScreeningMaoyan;
import com.family.grabserver.model.maoyan.ScreeningMaoyanModel;
import com.family.grabserver.service.ScreeningMaoyanService;
import com.family.grabserver.util.MaoyanPriceDecoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ScreeningMaoyanPipeline implements PageModelPipeline<ScreeningMaoyanModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ScreeningMaoyanService service;


  @Override
  public void process(ScreeningMaoyanModel model, Task task) {
    String context = model.getContext();

    Pattern p0 = Pattern.compile("(<span class=[\\w\\W]*?</i></span>)");
    Matcher m0 = p0.matcher(context);
    while (m0.find()) {
      String item = m0.group();
      String itemNew = item.replace("\\\"", "");
      itemNew = itemNew.replace("\"", "'");
      context = context.replace(item, itemNew);
    }

    String decodeCssContent = "";
    Pattern p = Pattern.compile("\"cssLink\":\"([\\w\\W]*css)\"}");
    Matcher m = p.matcher(context);
    if (m.find()) {
      String url = m.group(1);
      int retryTimes = 5;
      while (retryTimes > 0) {
        try {
          Thread.sleep(500);
          HttpGet request = new HttpGet(url);
          HttpResponse response = null;
          response = HttpClients.createDefault().execute(request);
          if (response.getStatusLine().getStatusCode() == 200) {
            decodeCssContent = EntityUtils.toString(response.getEntity());
            break;
          } else {
            logger.error("无法获取解码css内容" + context);
            retryTimes--;
            logger.error("重试次数:" + retryTimes + ",url:" + url);
            continue;
          }
        } catch (Exception e) {
          retryTimes--;
          logger.error("重试次数:" + retryTimes + ",url:" + url);
          continue;
        }
      }
    } else {
      logger.error("无法获取解码css地址" + context);
      return;
    }

    JSONObject ob = JSON.parseObject(context);
    JSONObject data = (JSONObject) ob.get("data");
    JSONObject dateShow = (JSONObject) data.get("DateShow");
    for (String key : dateShow.keySet()) {
      JSONArray shows = (JSONArray) dateShow.get(key);
      for (Object showOb : shows) {

        JSONObject show = (JSONObject) showOb;
        ScreeningMaoyan record = new ScreeningMaoyan();
        try {
          record.setId(show.getInteger("showId"));
          record.setCinemaId(Integer.parseInt(model.getCinemaid()));
          record.setMovieId(Integer.parseInt(model.getMovieid()));
          record.setShowDate(show.getDate("dt"));
          SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
          Date tm = sdf.parse(show.getString("tm"));
          record.setStartTime(tm);
          Date end = sdf.parse(show.getString("end"));
          record.setEndTime(end);

          record.setLanguage(show.getString("lang"));
          record.setHall(show.getString("th"));
          record.setVersion(show.getString("tp"));

          Float sellPr = MaoyanPriceDecoder.decode(show.getString("sellPrStr"), decodeCssContent);
          record.setSalePrice(sellPr);
          Float pr = MaoyanPriceDecoder.decode(show.getString("prStr"), decodeCssContent);
          record.setCinemaPrice(pr);

          String ticketURL = "http://m.maoyan.com/?tmp=seats&showId=" + show.getString("showId") +
            "&showDate=" + show.getString("showDate");
          record.setTicketUrl(ticketURL);
        } catch (ParseException e) {
          e.printStackTrace();
          continue;
        }
        try {
          service.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
