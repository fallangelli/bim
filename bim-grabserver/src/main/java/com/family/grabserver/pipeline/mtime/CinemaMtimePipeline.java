package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CinemaMtime;
import com.family.grabserver.model.mtime.CinemaMtimeModel;
import com.family.grabserver.service.mtime.CinemaMtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaMtimePipeline implements PageModelPipeline<CinemaMtimeModel> {

  @Autowired
  private CinemaMtimeService service;

  @Override
  public void process(CinemaMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject cinema = new JSONObject();
    try {
      cinema = JSON.parseObject(context);
    } catch (Exception e) {
      System.out.println(model.getContext());
      throw e;
    }
    CinemaMtime record = service.selectByPrimaryKey(Integer.parseInt(model.getCinemaId()));
    if (record != null && cinema.size() > 0) {
      record.setRating(cinema.getFloat("rating"));
      JSONObject feature = (JSONObject) cinema.get("feature");
      record.setHas3d(feature.getBoolean("has3D") == null ? false : feature.getBoolean("has3D"));
      record.setHasImax(feature.getBoolean("hasIMAX") == null ? false : feature.getBoolean("hasIMAX"));
      record.setHasWifi(feature.getBoolean("hasWifi") == null ? false : feature.getBoolean("hasWifi"));
      record.setHasPark(feature.getBoolean("hasPark") == null ? false : feature.getBoolean("hasPark"));

      service.insertOrUpdate(record);
    }
  }
}
