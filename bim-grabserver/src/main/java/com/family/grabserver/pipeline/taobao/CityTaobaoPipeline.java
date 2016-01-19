package com.family.grabserver.pipeline.taobao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CityTaobao;
import com.family.grabserver.model.taobao.CityTaobaoModel;
import com.family.grabserver.service.CityTaobaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityTaobaoPipeline implements PageModelPipeline<CityTaobaoModel> {

  @Autowired
  private CityTaobaoService service;


  @Override
  public void process(CityTaobaoModel model, Task task) {
    String context = model.getContext();
    context = context.substring(context.indexOf("(") + 1, context.length() - 1);
    JSONObject ob = JSON.parseObject(context);
    JSONObject dataob = ob.getJSONObject("data");
    JSONObject citys = dataob.getJSONObject("returnValue");
    for (String firstLetter : citys.keySet()) {
      JSONArray cityArray = citys.getJSONArray(firstLetter);
      for (Object cityOb : cityArray) {
        JSONObject city = (JSONObject) cityOb;

        CityTaobao record = new CityTaobao();
        record.setId(city.getInteger("cityCode"));
        record.setName(city.getString("regionName"));
        String py = city.getString("pinYin").toLowerCase();
        record.setPinyin(py);
        record.setFirstLetter(firstLetter.toUpperCase());

        service.insertOrUpate(record);
      }

    }

  }
}
