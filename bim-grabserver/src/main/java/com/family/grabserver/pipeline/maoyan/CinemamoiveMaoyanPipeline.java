package com.family.grabserver.pipeline.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CinemamovieMaoyan;
import com.family.grabserver.model.maoyan.CinemamovieMaoyanModel;
import com.family.grabserver.service.maoyan.CinemamovieMaoyanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemamoiveMaoyanPipeline implements PageModelPipeline<CinemamovieMaoyanModel> {

  @Autowired
  private CinemamovieMaoyanService service;

  @Override
  public void process(CinemamovieMaoyanModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONObject data = (JSONObject) ob.get("data");

    JSONArray movies = (JSONArray) data.get("movies");

    for (Object moiveOb : movies) {

      JSONObject moive = (JSONObject) moiveOb;
      CinemamovieMaoyan record = new CinemamovieMaoyan();

      record.setMovieId(moive.getInteger("id"));
      record.setCinemaId(Integer.parseInt(model.getCinemaid()));

      service.insert(record);
    }
  }
}
