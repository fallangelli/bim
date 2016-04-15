package com.family.grabserver.pipeline.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CityareaBaidu;
import com.family.grabserver.model.baidu.CityareaBaiduModel;
import com.family.grabserver.service.baidu.CityareaBaiduService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityareaBaiduPipeline implements PageModelPipeline<CityareaBaiduModel> {
  private Logger logger = Logger.getLogger(getClass());

  @Autowired
  private CityareaBaiduService service;

  @Override
  public void process(CityareaBaiduModel model, Task task) {

    String context = model.getArea();
    JSONArray areas = JSON.parseArray(context);
    for (Object areaOb : areas) {
      JSONObject area = (JSONObject) areaOb;
      CityareaBaidu record = new CityareaBaidu();
      record.setId(area.getInteger("id"));
      record.setCityId(Integer.parseInt(model.getCityId()));
      record.setCityName(model.getCityName());
      record.setName(area.getString("name"));

      try {
        service.insertOrUpdate(record);
      } catch (org.springframework.dao.DuplicateKeyException e) {
        String log = e.getMessage();
        logger.warn(log);
      }
    }
  }
}
