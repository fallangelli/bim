package com.family.grabserver.pipeline.taobao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CinemaTaobao;
import com.family.grabserver.model.taobao.CinemaTaobaoModel;
import com.family.grabserver.service.CinemaTaobaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaTaobaoPipeline implements PageModelPipeline<CinemaTaobaoModel> {

  @Autowired
  private CinemaTaobaoService service;

  @Override
  public void process(CinemaTaobaoModel model, Task task) {
    String context = model.getContext();
    context = context.substring(context.indexOf("(") + 1, context.length() - 1);
    JSONObject ob = JSON.parseObject(context);
    JSONObject dataob = ob.getJSONObject("data");
    JSONObject Cinemas = dataob.getJSONObject("returnValue");
    JSONObject regionCinemas = Cinemas.getJSONObject("regionCinemas");
    for (String area : regionCinemas.keySet()) {
      JSONArray CinemaArray = regionCinemas.getJSONArray(area);
      for (Object CinemaOb : CinemaArray) {
        JSONObject Cinema = (JSONObject) CinemaOb;

        CinemaTaobao record = new CinemaTaobao();
        record.setId(Cinema.getInteger("id"));
        record.setCityId(Integer.parseInt(model.getCityId()));
        record.setName(Cinema.getString("cinemaName"));
        record.setArea(area);
        record.setAddress(Cinema.getString("address"));
        record.setLatitude(Cinema.getString("latitude"));
        record.setLongitude(Cinema.getString("longitude"));
        record.setPreferential(Cinema.get("activities") != null ? true : false);
        record.setTel(Cinema.getString("phone"));

        service.insertOrUpdate(record);
      }

    }

  }
}
