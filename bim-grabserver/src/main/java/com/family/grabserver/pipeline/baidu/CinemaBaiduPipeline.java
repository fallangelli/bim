package com.family.grabserver.pipeline.baidu;

import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.CinemaBaidu;
import com.family.grabserver.model.baidu.CinemaBaiduModel;
import com.family.grabserver.service.CinemaBaiduService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CinemaBaiduPipeline implements PageModelPipeline<CinemaBaiduModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CinemaBaiduService service;

  @Override
  public void process(CinemaBaiduModel model, Task task) {
    if (model.getListId().size() != model.getListName().size()
      || model.getListName().size() != model.getListAddress().size() ||
      model.getListId().size() != model.getListAddress().size()) {
      logger.error("百度影院编号、名称或地址数据长度不一致");
      return;
    }

    List<String> listId = model.getListId();
    for (int i = 0; i < listId.size(); i++) {
      CinemaBaidu record = new CinemaBaidu();
      record.setId(Integer.parseInt(listId.get(i)));
      record.setName(model.getListName().get(i));
      record.setCityId(Integer.parseInt(model.getCityId()));
      record.setArea(model.getAreaName());
      record.setAddress(model.getListAddress().get(i));

      service.insertOrUpate(record);
    }

  }
}
