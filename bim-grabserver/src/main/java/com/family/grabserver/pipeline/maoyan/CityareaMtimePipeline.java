package com.family.grabserver.pipeline.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CinemaMtime;
import com.family.grabserver.entity.bim_grab.CityareaMtime;
import com.family.grabserver.model.mtime.CityareaMtimeModel;
import com.family.grabserver.service.CinemaMtimeService;
import com.family.grabserver.service.CityareaMtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityareaMtimePipeline implements PageModelPipeline<CityareaMtimeModel> {

  @Autowired
  private CityareaMtimeService service;
  @Autowired
  private CinemaMtimeService cinemaService;


  @Override
  public void process(CityareaMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONArray districts = ob.getJSONArray("districts");

    for (Object districtOb : districts) {
      JSONObject district = (JSONObject) districtOb;
      CityareaMtime record = new CityareaMtime();
      record.setId(district.getInteger("id"));
      record.setCityId(Integer.parseInt(model.getCityId()));
      record.setName(district.getString("name"));

      service.insertOrUpate(record);
    }

    JSONArray cinemas = ob.getJSONArray("cinemas");

    for (Object cinemaOb : cinemas) {
      JSONObject cinema = (JSONObject) cinemaOb;
      CinemaMtime record = new CinemaMtime();
      record.setId(cinema.getInteger("id"));
      if (cinema.getInteger("districtId") == 0) {
        record.setArea("市区");
      } else {
        CityareaMtime area = service.selectByPrimaryKey(cinema.getInteger("districtId"));
        record.setArea(area.getName());
      }

      record.setCityId(Integer.parseInt(model.getCityId()));
      record.setName(cinema.getString("name"));
      record.setAddress(cinema.getString("address"));
      record.setLatitude(cinema.getString("latitude"));
      record.setLongitude(cinema.getString("longitude"));
      record.setTel(cinema.getString("tele"));
      record.setRoute(cinema.getString("route"));


      cinemaService.insertOrUpate(record);
    }
  }
}
