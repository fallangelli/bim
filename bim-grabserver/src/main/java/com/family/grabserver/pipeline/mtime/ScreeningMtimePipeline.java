package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.ScreeningMtime;
import com.family.grabserver.model.mtime.ScreeningMtimeModel;
import com.family.grabserver.service.ScreeningMtimeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class ScreeningMtimePipeline implements PageModelPipeline<ScreeningMtimeModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ScreeningMtimeService service;

  @Override
  public void process(ScreeningMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONArray shows = (JSONArray) ob.get("s");
    for (Object showOb : shows) {

      JSONObject show = (JSONObject) showOb;

      if (show.getJSONArray("provider").size() <= 0 || show.getFloat("startTime") < 0) {
        continue;
      }

      ScreeningMtime record = new ScreeningMtime();


      record.setId(show.getInteger("sid"));
      JSONArray providers = show.getJSONArray("provider");
      for (Object providerOb : providers) {
        JSONObject provider = (JSONObject) providerOb;
        String ticketURL = "http://m.mtime.cn/#!/onlineticket/" + provider.getInteger("dId") + "/";
        record.setTicketUrl(ticketURL);
      }

      record.setCinemaId(Integer.parseInt(model.getCinemaId()));
      record.setMovieId(Integer.parseInt(model.getMovieId()));

      DateFormat dtfmt = new SimpleDateFormat("yyyy-MM-dd");
      try {
        record.setShowDate(dtfmt.parse(model.getShowDate()));
      } catch (ParseException e) {
        logger.error("无法解析上映时间" + context);
        e.printStackTrace();
        return;
      }

      long showDay = show.getLong("showDay");
      Calendar cal = new GregorianCalendar();
      cal.clear();
      int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
      cal.setTimeInMillis(showDay * 1000 - zoneOffset);
      record.setStartTime(cal.getTime());
      cal.add(Calendar.MINUTE, show.getInteger("length"));
      record.setEndTime(cal.getTime());

      record.setLanguage(show.getString("language"));

      record.setHall(show.getString("hall"));

      record.setVersion(show.getString("versionDesc"));
      record.setSalePrice(show.getFloat("salePrice") / 100);
      record.setCinemaPrice(show.getFloat("cinemaPrice") / 100);

      try {
        service.insertOrUpate(record);
      } catch (DuplicateKeyException de) {
        logger.warn("猫眼上映信息键值重复");
      }

    }
  }
}
