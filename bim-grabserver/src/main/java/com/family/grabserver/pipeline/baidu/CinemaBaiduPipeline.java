package com.family.grabserver.pipeline.baidu;

import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_base.City;
import com.family.grabserver.entity.bim_base.Cityarea;
import com.family.grabserver.entity.bim_grab.CinemaBaidu;
import com.family.grabserver.model.baidu.CinemaBaiduModel;
import com.family.grabserver.service.CityService;
import com.family.grabserver.service.baidu.CinemaBaiduService;
import com.family.grabserver.util.CityMerge;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CinemaBaiduPipeline implements PageModelPipeline<CinemaBaiduModel> {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CinemaBaiduService service;
  @Autowired
  private CityService cservice;

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

      record.setBaiduCityName(model.getCityName());
      record.setBaiduArea(CityMerge.getAreaWithTrans(model.getCityName(), model.getAreaName()));
      record.setAddress(model.getListAddress().get(i));
      service.insertOrUpdate(record);

      City simCity = cservice.getMostSimilarCity(model.getCityName());
      if (simCity != null) {
        record.setCityId(simCity.getId());
        record.setCityName(simCity.getName());

        Cityarea simArea = cservice.getMostSimilarArea(simCity.getId(),
          record.getBaiduCityName(), record.getBaiduArea());
        if (simArea != null) {
          record.setAreaId(simArea.getId());
          record.setAreaName(simArea.getName());
        } else
          logger.error("无法找到归并地区：" + record.getBaiduCityName() + " - " + record.getBaiduArea());
      } else
        logger.error("无法找到归并城市：" + model.getCityName());


      service.insertOrUpdate(record);
    }

  }
}
