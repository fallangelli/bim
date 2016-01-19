package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.MovieshowingMtime;
import com.family.grabserver.model.mtime.MovieshowingMtimeModel;
import com.family.grabserver.service.MovieshowingMtimeService;
import com.family.grabserver.util.JsonStringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieshowingMtimePipeline implements PageModelPipeline<MovieshowingMtimeModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MovieshowingMtimeService service;


  @Override
  public void process(MovieshowingMtimeModel model, Task task) {
    String context = model.getContext();

    JSONObject movie = JSON.parseObject(context);

    MovieshowingMtime record = new MovieshowingMtime();
    record.setId(Integer.parseInt(model.getMovieId()));
    record.setName(movie.getString("titleCn"));
    record.setImage(movie.getString("image"));
    record.setRating(movie.getString("rating"));
    record.setIsImax(movie.getBoolean("isIMAX"));
    record.setIs3d(movie.getBoolean("is3D"));

    JSONArray dirs = movie.getJSONArray("directors");
    record.setDirectors(JsonStringUtil.toSplitString(dirs));

    JSONArray actors = movie.getJSONArray("actors");
    record.setActors(JsonStringUtil.toSplitString(actors));

    JSONArray types = movie.getJSONArray("type");
    record.setType(JsonStringUtil.toSplitString(types));

    record.setContent(movie.getString("content"));
    record.setRuntime(movie.getString("runTime"));
    service.insertOrUpate(record);
  }
}
