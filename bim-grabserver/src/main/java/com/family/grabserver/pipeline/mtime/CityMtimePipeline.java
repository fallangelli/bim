package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.CityMtime;
import com.family.grabserver.model.mtime.CityMtimeModel;
import com.family.grabserver.service.CityMtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMtimePipeline implements PageModelPipeline<CityMtimeModel> {

  @Autowired
  private CityMtimeService service;


  @Override
  public void process(CityMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONArray citys = (JSONArray) ob.get("p");
    for (Object cityOb : citys) {
      JSONObject city = (JSONObject) cityOb;
      CityMtime record = new CityMtime();
      record.setId(city.getInteger("id"));
      record.setName(city.getString("n"));

      service.insertOrUpate(record);
    }
    //删除 时光市
    service.deleteByPrimaryKey(5051);
  }
}
