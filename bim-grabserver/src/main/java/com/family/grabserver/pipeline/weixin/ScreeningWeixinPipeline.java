package com.family.grabserver.pipeline.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.ScreeningWeixin;
import com.family.grabserver.model.Weixin.ScreeningWeixinModel;
import com.family.grabserver.service.weixin.MovieshowingWeixinService;
import com.family.grabserver.service.weixin.ScreeningWeixinService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ScreeningWeixinPipeline implements PageModelPipeline<ScreeningWeixinModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ScreeningWeixinService service;
  @Autowired
  private MovieshowingWeixinService mService;

  @Override
  public void process(ScreeningWeixinModel model, Task task) {
    String context = model.getContext();
    JSONArray movies = JSON.parseArray(context);

    for (Object movieObj : movies) {
      JSONObject movie = (JSONObject) movieObj;
      JSONObject sources = movie.getJSONObject("sche");
      Set<String> keys = sources.keySet();
      for (String key : keys) {
        Integer cinmaId = Integer.parseInt(model.getCinemaId());
        Integer movieId = movie.getInteger("id");
        DateFormat dtfmt = new SimpleDateFormat("yyyyMMdd");

        JSONArray languages = sources.getJSONArray(key);

        for (Object languageObj : languages) {
          JSONObject language = (JSONObject) languageObj;

          JSONArray seats = language.getJSONArray("seat_info");
          for (Object seatObj : seats) {
            JSONObject seat = (JSONObject) seatObj;
            ScreeningWeixin record = new ScreeningWeixin();

            record.setCinemaId(cinmaId);
            record.setMovieId(movieId);

            try {
              record.setShowDate(dtfmt.parse(key));
            } catch (ParseException e) {
              e.printStackTrace();
              continue;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
              Date tm = sdf.parse(seat.getString("time"));
              record.setStartTime(tm);
            } catch (ParseException e) {
              e.printStackTrace();
              continue;
            }

            Pattern pattern = Pattern.compile("(\\d*)");
            Matcher matcher = pattern.matcher(mService.selectByPrimaryKey(movieId).getRuntime());
            if (matcher.find()) {
              String runTime = matcher.group();
              Integer lastTime = Integer.parseInt(runTime);
              if (lastTime == null || lastTime <= 0)
                continue;
              Calendar calEndTime = Calendar.getInstance();
              calEndTime.setTime(record.getStartTime());
              calEndTime.add(Calendar.MINUTE, lastTime);
              record.setEndTime(calEndTime.getTime());
            }

            record.setLanguage(seat.getString("lagu"));
            record.setHall(seat.getString("roomname"));
            record.setVersion(seat.getString("type"));
            record.setSalePrice(seat.getFloat("price"));
            record.setCinemaPrice(seat.getFloat("cinema_price"));
            record.setTicketUrl("http://m.wepiao.com/room.html?movie_id=" + movieId + "&mpid=" + seat.getFloat("mpid"));

            service.insert(record);
          }

        }
      }
    }
  }
}
