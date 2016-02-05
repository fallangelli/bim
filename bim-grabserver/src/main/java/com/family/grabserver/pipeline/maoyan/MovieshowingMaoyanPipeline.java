package com.family.grabserver.pipeline.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.MovieshowingMaoyan;
import com.family.grabserver.model.maoyan.MovieshowingMaoyanModel;
import com.family.grabserver.service.maoyan.MovieshowingMaoyanService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MovieshowingMaoyanPipeline implements PageModelPipeline<MovieshowingMaoyanModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MovieshowingMaoyanService service;


  @Override
  public void process(MovieshowingMaoyanModel model, Task task) {
    String context = model.getContext();
    try {
      Pattern p1 = Pattern.compile("(\\{[\\w\\W]*\\})");
      Matcher m1 = p1.matcher(context);
      if (m1.find()) {
        context = m1.group(1);
      }

      JSONObject ob = JSON.parseObject(context);
      JSONObject data = (JSONObject) ob.get("data");
      JSONObject movie = (JSONObject) data.get("MovieDetailModel");

      MovieshowingMaoyan record = new MovieshowingMaoyan();
      record.setId(movie.getInteger("id"));
      record.setName(movie.getString("nm"));
      record.setImage(movie.getString("img"));
      record.setRating(movie.getFloat("sc"));
      record.setIsImax(movie.getBoolean("imax"));
      record.setIs3d(movie.getBoolean("3d"));
      record.setDirectors(movie.getString("dir"));
      record.setActors(movie.getString("star"));
      record.setType(movie.getString("cat"));
      record.setContent(movie.getString("scm"));
      record.setRuntime(movie.getString("dur"));
      record.setVer(movie.getString("ver"));
      service.insertOrUpdate(record);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("抓取电影信息错误:" + context);
    }
  }
}
