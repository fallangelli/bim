package com.family.grabserver.pipeline.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CityMaoyan;
import com.family.grabserver.model.maoyan.CityMaoyanModel;
import com.family.grabserver.service.CityMaoyanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMaoyanPipeline implements PageModelPipeline<CityMaoyanModel> {

  @Autowired
  private CityMaoyanService service;

  @Override
  public void process(CityMaoyanModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONObject data = (JSONObject) ob.get("data");
    JSONObject cityMap = (JSONObject) data.get("CityMap");
    for (String key : cityMap.keySet()) {
      JSONArray citys = (JSONArray) cityMap.get(key);
      for (Object cityOb : citys) {
        JSONObject city = (JSONObject) cityOb;
        CityMaoyan record = new CityMaoyan();
        record.setId(city.getInteger("id"));
        record.setName(city.getString("name"));
        record.setPinyin(city.getString("pinyin").toLowerCase());
        record.setFirstLetter(city.getString("pinyin").substring(0, 1).toUpperCase());

        service.insertOrUpdate(record);
      }
    }
  }
}
