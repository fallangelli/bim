package com.family.grabserver.pipeline.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CityareaBaidu;
import com.family.grabserver.model.baidu.CityareaBaiduModel;
import com.family.grabserver.service.baidu.CityareaBaiduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityareaBaiduPipeline implements PageModelPipeline<CityareaBaiduModel> {

  @Autowired
  private CityareaBaiduService service;

  @Override
  public void process(CityareaBaiduModel model, Task task) {

    String context = model.getArea();
    JSONObject ob = JSON.parseObject(context);
    JSONArray areas = ob.getJSONObject("cinemaStr").getJSONObject("filter").getJSONArray("areas");
    for (Object areaOb : areas) {
      JSONObject area = (JSONObject) areaOb;
      CityareaBaidu record = new CityareaBaidu();
      record.setId(area.getInteger("id"));
      record.setCityId(Integer.parseInt(model.getCityId()));
      record.setCityName(model.getCityName());
      record.setName(area.getString("name"));

      service.insertOrUpdate(record);
    }
  }
}
