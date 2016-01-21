package com.family.grabserver.pipeline.baidu;

import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.CityBaidu;
import com.family.grabserver.model.baidu.CityBaiduModel;
import com.family.grabserver.service.CityBaiduService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityBaiduPipeline implements PageModelPipeline<CityBaiduModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CityBaiduService service;


  @Override
  public void process(CityBaiduModel model, Task task) {
    if (model.getListId().size() != model.getListName().size()) {
      logger.error("百度城市编号和名称数据长度不一致");
      return;
    }
    List<String> listId = model.getListId();
    for (int i = 0; i < listId.size(); i++) {
      CityBaidu record = new CityBaidu();
      record.setId(Integer.parseInt(listId.get(i)));
      String name = model.getListName().get(i);
      record.setName(name);

      service.insertOrUpate(record);
    }

  }
}
