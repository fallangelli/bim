package com.family.grabserver.pipeline.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_base.City;
import com.family.grabserver.entity.bim_base.Cityarea;
import com.family.grabserver.entity.bim_grab.CinemaWeixin;
import com.family.grabserver.model.Weixin.CinemaWeixinModel;
import com.family.grabserver.service.CityService;
import com.family.grabserver.service.weixin.CinemaWeixinService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaWeixinPipeline implements PageModelPipeline<CinemaWeixinModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CinemaWeixinService service;
  @Autowired
  private CityService cservice;


  @Override
  public void process(CinemaWeixinModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    if (ob.get("info") == null)
      return;
    JSONArray cinemas = (JSONArray) ob.get("info");

    for (Object obj : cinemas) {
      JSONObject cinema = (JSONObject) obj;
      CinemaWeixin record = new CinemaWeixin();
      record.setId(cinema.getInteger("id"));
      record.setWeixinCityId(Integer.parseInt(model.getCityId()));
      record.setWeixinCityName(model.getCityName());
      record.setWeixinArea(cinema.getString("area_name"));

      record.setName(cinema.getString("name"));
      record.setAddress(cinema.getString("addr"));
      record.setLatitude(cinema.getString("latitude"));
      record.setLongitude(cinema.getString("longitude"));
      record.setTel(cinema.getString("tele"));

      City simCity = cservice.getMostSimilarCity(model.getCityName());
      if (simCity != null) {
        record.setCityId(simCity.getId());
        record.setCityName(simCity.getName());

        Cityarea simArea = cservice.getMostSimilarArea(simCity.getId(),
          record.getWeixinCityName(), record.getWeixinArea());
        if (simArea != null) {
          record.setAreaId(simArea.getId());
          record.setAreaName(simArea.getName());
        } else
          logger.error("无法找到归并地区：" + record.getWeixinCityName() + " - " + record.getWeixinArea());
      } else
        logger.error("无法找到归并城市：" + model.getCityName());


      service.insertOrUpdate(record);
    }

  }
}
