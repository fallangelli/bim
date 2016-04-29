package com.family.grabserver.pipeline.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_base.City;
import com.family.grabserver.entity.bim_base.Cityarea;
import com.family.grabserver.entity.bim_grab.CinemaMaoyan;
import com.family.grabserver.model.maoyan.CinemaMaoyanModel;
import com.family.grabserver.service.CityService;
import com.family.grabserver.service.maoyan.CinemaMaoyanService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaMaoyanPipeline implements PageModelPipeline<CinemaMaoyanModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CinemaMaoyanService service;
  @Autowired
  private CityService cservice;

  @Override
  public void process(CinemaMaoyanModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONObject data = (JSONObject) ob.get("data");

    if (model.getCityId().compareToIgnoreCase("1") != 0 && context.contains("密云县")) {
      logger.error(model.getCityName() + ":目标城市错误");
    }
    for (String key : data.keySet()) {
      String area = key;
      JSONArray cinemaArray = (JSONArray) data.get(key);
      for (Object cinemaOb : cinemaArray) {
        JSONObject cinema = (JSONObject) cinemaOb;
        CinemaMaoyan record = new CinemaMaoyan();
        record.setId(cinema.getInteger("id"));
        record.setMaoyanCityId(Integer.parseInt(model.getCityId()));
        record.setMaoyanCityName(model.getCityName());
        record.setMaoyanArea(cinema.getString("area"));
        record.setName(cinema.getString("nm"));
        record.setAddress(cinema.getString("addr"));

        service.insertOrUpdate(record);

        City simCity = cservice.getMostSimilarCity(model.getCityName());
        if (simCity != null) {
          record.setCityId(simCity.getId());
          record.setCityName(simCity.getName());

          Cityarea simArea = cservice.getMostSimilarArea(simCity.getId(),
            record.getMaoyanCityName(), record.getMaoyanArea());
          if (simArea != null) {
            record.setAreaId(simArea.getId());
            record.setAreaName(simArea.getName());
          } else
            logger.warn("无法找到归并地区：" + record.getMaoyanCityName() + " - " + record.getMaoyanArea());
        } else
          logger.warn("无法找到归并城市：" + model.getCityName());


        service.insertOrUpdate(record);
      }
    }

  }
}
