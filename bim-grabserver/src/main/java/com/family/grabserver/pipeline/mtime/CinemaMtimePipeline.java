package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.CinemaMtime;
import com.family.grabserver.model.mtime.CinemaMtimeModel;
import com.family.grabserver.service.CinemaMtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaMtimePipeline implements PageModelPipeline<CinemaMtimeModel> {

  @Autowired
  private CinemaMtimeService service;

  @Override
  public void process(CinemaMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject cinema = JSON.parseObject(context);

    CinemaMtime record = service.selectByPrimaryKey(Integer.parseInt(model.getCinemaId()));
    if (record != null) {
      record.setRating(cinema.getString("rating"));
      JSONObject feature = (JSONObject) cinema.get("feature");
      record.setHas3d(feature.getBoolean("has3D") == null ? false : feature.getBoolean("has3D"));
      record.setHasImax(feature.getBoolean("hasIMAX") == null ? false : feature.getBoolean("hasIMAX"));
      record.setHasWifi(feature.getBoolean("hasWifi") == null ? false : feature.getBoolean("hasWifi"));
      record.setHasPark(feature.getBoolean("hasPark") == null ? false : feature.getBoolean("hasPark"));

      service.insertOrUpate(record);
    }
  }
}
