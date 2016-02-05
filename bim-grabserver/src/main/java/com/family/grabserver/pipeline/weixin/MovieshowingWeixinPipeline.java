package com.family.grabserver.pipeline.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.MovieshowingWeixin;
import com.family.grabserver.model.Weixin.MovieshowingWeixinModel;
import com.family.grabserver.service.CityService;
import com.family.grabserver.service.weixin.MovieshowingWeixinService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieshowingWeixinPipeline implements PageModelPipeline<MovieshowingWeixinModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MovieshowingWeixinService service;
  @Autowired
  private CityService cservice;


  @Override
  public void process(MovieshowingWeixinModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    if (ob.get("info") == null)
      return;
    JSONArray movies = (JSONArray) ob.get("info");

    for (Object obj : movies) {
      JSONObject movie = (JSONObject) obj;
      MovieshowingWeixin record = new MovieshowingWeixin();
      record.setId(movie.getInteger("id"));
      record.setName(movie.getString("name"));
      record.setImage(movie.getString("poster_url"));
      record.setRating(movie.getFloat("score"));
      record.setDirectors(movie.getString("director"));
      record.setActors(movie.getString("actor"));
      record.setType(movie.getString("tags"));
      record.setContent(movie.getString("detail"));
      record.setRuntime(movie.getString("duration"));
      record.setVer(movie.getString("version"));

      if (service.selectByPrimaryKey(movie.getInteger("id")) == null)
        service.insertOrUpdate(record);
    }

  }
}
