package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_base.City;
import com.family.grabserver.entity.bim_base.Cityarea;
import com.family.grabserver.entity.bim_grab.CinemaMtime;
import com.family.grabserver.entity.bim_grab.CityareaMtime;
import com.family.grabserver.model.mtime.CityareaMtimeModel;
import com.family.grabserver.service.CinemaMtimeService;
import com.family.grabserver.service.CityService;
import com.family.grabserver.service.CityareaMtimeService;
import com.family.grabserver.util.CityMerge;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityareaMtimePipeline implements PageModelPipeline<CityareaMtimeModel> {

  @Autowired
  private CityareaMtimeService service;
  @Autowired
  private CinemaMtimeService cinemaService;
  @Autowired
  private CityService cservice;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void process(CityareaMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONArray districts = ob.getJSONArray("districts");

    for (Object districtOb : districts) {
      JSONObject district = (JSONObject) districtOb;
      CityareaMtime record = new CityareaMtime();
      record.setId(district.getInteger("id"));
      record.setCityName(model.getCityName());
      record.setCityId(Integer.parseInt(model.getCityId()));
      record.setName(district.getString("name"));

      service.insertOrUpdate(record);
    }

    JSONArray cinemas = ob.getJSONArray("cinemas");

    for (Object cinemaOb : cinemas) {
      JSONObject cinema = (JSONObject) cinemaOb;
      CinemaMtime record = new CinemaMtime();
      record.setId(cinema.getInteger("id"));
      record.setMtimeCityName(model.getCityName());
      if (cinema.getInteger("districtId") == 0)
        record.setMtimeArea(model.getCityName());
      else {
        CityareaMtime area = service.selectByPrimaryKey(cinema.getInteger("districtId"));
        record.setMtimeArea(CityMerge.getAreaWithTrans(model.getCityName(), area.getName()));
      }
      record.setCityId(Integer.parseInt(model.getCityId()));
      record.setName(cinema.getString("name"));
      record.setAddress(cinema.getString("address"));
      record.setLatitude(cinema.getString("latitude"));
      record.setLongitude(cinema.getString("longitude"));
      record.setTel(cinema.getString("tele"));
      record.setRoute(cinema.getString("route"));

      cinemaService.insertOrUpdate(record);

      City simCity = cservice.getMostSimilarCity(model.getCityName());
      if (simCity != null) {
        record.setCityId(simCity.getId());
        record.setCityName(simCity.getName());

        Cityarea simArea = cservice.getMostSimilarArea(simCity.getId(), record.getMtimeCityName(), record.getMtimeArea());
        if (simArea != null) {
          record.setAreaId(simArea.getId());
          record.setAreaName(simArea.getName());
        } else
          logger.error("无法找到归并地区：" + model.getCityName() + " - " + record.getMtimeArea());
      } else
        logger.error("无法找到归并城市：" + model.getCityName());

      cinemaService.insertOrUpdate(record);
    }
  }
}
