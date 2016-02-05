package com.family.grabserver.pipeline.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CityWeixin;
import com.family.grabserver.model.Weixin.CityWeixinModel;
import com.family.grabserver.service.weixin.CityWeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CityWeixinPipeline implements PageModelPipeline<CityWeixinModel> {

  @Autowired
  private CityWeixinService service;


  @Override
  public void process(CityWeixinModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONObject citys = (JSONObject) ob.get("list");
    Set<String> keys = citys.keySet();
    for (String key : keys) {
      JSONObject cityObj = citys.getJSONObject(key);
      CityWeixin record = new CityWeixin();
      record.setId(Integer.parseInt(key));
      record.setName(cityObj.getString("name"));

      service.insertOrUpdate(record);
    }

  }
}
