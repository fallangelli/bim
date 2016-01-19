package com.family.grabserver.pipeline.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CinemaMaoyan;
import com.family.grabserver.model.maoyan.CinemaMaoyanModel;
import com.family.grabserver.service.CinemaMaoyanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaMaoyanPipeline implements PageModelPipeline<CinemaMaoyanModel> {

  @Autowired
  private CinemaMaoyanService service;

  @Override
  public void process(CinemaMaoyanModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONObject data = (JSONObject) ob.get("data");
    for (String key : data.keySet()) {
      String area = key;
      JSONArray cinemaArray = (JSONArray) data.get(key);
      for (Object cinemaOb : cinemaArray) {
        JSONObject cinema = (JSONObject) cinemaOb;
        CinemaMaoyan record = new CinemaMaoyan();
        record.setId(cinema.getInteger("id"));
        record.setCityId(Integer.parseInt(model.getCityId()));
        record.setName(cinema.getString("nm"));
        record.setArea(cinema.getString("area"));
        record.setAddress(cinema.getString("addr"));

        record.setLatitude(cinema.getString("lat"));
        record.setLongitude(cinema.getString("lng"));

        record.setSell(cinema.getBoolean("sell"));
        record.setPreferential(cinema.getBoolean("preferential"));
        record.setHasImax(cinema.getBoolean("imax"));

//todo
//                record.setTel();

        service.insertOrUpate(record);
      }
    }

  }
}
