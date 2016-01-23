package com.family.grabserver.pipeline.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.MovieshowingBaidu;
import com.family.grabserver.entity.bim_grab.ScreeningBaidu;
import com.family.grabserver.model.baidu.ScreeningBaiduModel;
import com.family.grabserver.service.MovieshowingBaiduService;
import com.family.grabserver.service.ScreeningBaiduService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class ScreeningBaiduPipeline implements PageModelPipeline<ScreeningBaiduModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ScreeningBaiduService service;
  @Autowired
  private MovieshowingBaiduService movieService;

  @Override
  public void process(ScreeningBaiduModel model, Task task) {
    try {
      String context = model.getContext();
      JSONArray movies = JSON.parseObject(context).getJSONArray("movies");
      for (Object movieOb : movies) {
        JSONObject movie = (JSONObject) movieOb;

        Integer movieId = movie.getInteger("movieId");
        MovieshowingBaidu movieRecord = movieService.selectByPrimaryKey(movieId);
        if (movieRecord == null) {
          movieRecord = new MovieshowingBaidu();
          movieRecord.setId(movieId);
          movieRecord.setName(movie.getString("name"));
          movieRecord.setContent(movie.getString("summary"));
          movieRecord.setImage(movie.getJSONObject("posterUrl").getString("medium"));
          movieRecord.setRating(movie.getDouble("score"));
          movieService.insertOrUpdate(movieRecord);
        }

        JSONArray schedules = movie.getJSONArray("schedules");
        for (Object scheduleOb : schedules) {
          JSONObject schedule = (JSONObject) scheduleOb;

          long showDay = schedule.getLong("date");
          Calendar cal = new GregorianCalendar();
          cal.clear();
          cal.setTimeInMillis(showDay);
          Date showDate = cal.getTime();


          JSONArray dailySchedules = schedule.getJSONArray("dailySchedules");
          for (Object dailyScheduleOb : dailySchedules) {
            JSONObject dailySchedule = (JSONObject) dailyScheduleOb;

            ScreeningBaidu record = new ScreeningBaidu();
            record.setMovieId(movieId);
            Integer cinemaId = Integer.parseInt(model.getCinemaId());
            String seqNo = dailySchedule.getString("seqNo");
            record.setCinemaId(cinemaId);
            record.setShowDate(showDate);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date tm = sdf.parse(dailySchedule.getString("start"));
            record.setStartTime(tm);
            tm = sdf.parse(dailySchedule.getString("end"));
            record.setEndTime(tm);
            record.setLanguage(dailySchedule.getString("language"));
            record.setHall(dailySchedule.getString("theaterName"));
            record.setVersion(dailySchedule.getString("version"));
            record.setSalePrice(dailySchedule.getFloat("price"));
            record.setCinemaPrice(dailySchedule.getFloat("originalPrice"));
            record.setTicketUrl("http://m.dianying.baidu.com/ticket/select?movieId=" +
              movieId + "&cinemaId=" + cinemaId + "&seqNo=" + seqNo + "&date=" + showDay + "&orderId=");
            service.insert(record);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
