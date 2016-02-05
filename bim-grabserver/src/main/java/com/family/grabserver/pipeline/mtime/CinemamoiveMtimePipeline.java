package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CinemamovieMtime;
import com.family.grabserver.model.mtime.CinemamovieMtimeModel;
import com.family.grabserver.service.mtime.CinemaMtimeService;
import com.family.grabserver.service.mtime.CinemamovieMtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemamoiveMtimePipeline implements PageModelPipeline<CinemamovieMtimeModel> {
  @Autowired
  private CinemaMtimeService cinemaService;
  @Autowired
  private CinemamovieMtimeService service;

  @Override
  public void process(CinemamovieMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONArray movies = (JSONArray) ob.get("movies");

    for (Object movieOb : movies) {

      JSONObject movie = (JSONObject) movieOb;

      CinemamovieMtime record = new CinemamovieMtime();

      record.setMovieId(movie.getInteger("movieId"));
      record.setCinemaId(Integer.parseInt(model.getCinemaid()));
      record.setShowDates(movie.getString("showDates"));
      service.insertOrUpdate(record);
    }
  }
}
