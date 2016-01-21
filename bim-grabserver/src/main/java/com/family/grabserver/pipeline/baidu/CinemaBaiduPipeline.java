package com.family.grabserver.pipeline.baidu;

import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.CinemaBaidu;
import com.family.grabserver.entity.City;
import com.family.grabserver.entity.Cityarea;
import com.family.grabserver.model.baidu.CinemaBaiduModel;
import com.family.grabserver.service.CinemaBaiduService;
import com.family.grabserver.service.CityService;
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
      if (model.getAreaName().compareTo("白下") == 0 && model.getCityName().compareTo("南京") == 0)
        model.setAreaName("秦淮区");
      if (model.getAreaName().compareTo("下关") == 0 && model.getCityName().compareTo("南京") == 0)
        model.setAreaName("鼓楼区");
      if (model.getAreaName().compareTo("卢湾") == 0 && model.getCityName().compareTo("上海") == 0)
        model.setAreaName("黄浦区");
      if (model.getAreaName().compareTo("新浦") == 0 && model.getCityName().compareTo("连云港") == 0)
        model.setAreaName("海州区");
      if (model.getAreaName().compareTo("东陵") == 0 && model.getCityName().compareTo("沈阳") == 0)
        model.setAreaName("浑南区");
      if (model.getAreaName().compareTo("沧浪") == 0 && model.getCityName().compareTo("苏州") == 0)
        model.setAreaName("姑苏区");
      if (model.getAreaName().compareTo("平江") == 0 && model.getCityName().compareTo("苏州") == 0)
        model.setAreaName("姑苏区");
      if (model.getAreaName().compareTo("金阊") == 0 && model.getCityName().compareTo("苏州") == 0)
        model.setAreaName("姑苏区");
      if (model.getAreaName().compareTo("桥东") == 0 && model.getCityName().compareTo("石家庄") == 0)
        model.setAreaName("桥西区");
      if (model.getAreaName().compareTo("楚州") == 0 && model.getCityName().compareTo("淮安") == 0)
        model.setAreaName("淮安区");
      if (model.getAreaName().compareTo("铜仁") == 0 && model.getCityName().compareTo("铜仁") == 0)
        model.setAreaName("市辖区");
      if (model.getAreaName().compareTo("绍兴") == 0 && model.getCityName().compareTo("绍兴") == 0)
        model.setAreaName("市辖区");


      record.setBaiduArea(model.getAreaName());
      record.setAddress(model.getListAddress().get(i));
      service.insertOrUpate(record);

      City simCity = cservice.getMostSimilarCity(model.getCityName());
      if (simCity != null) {
        record.setCityId(simCity.getId());
        record.setCityName(simCity.getName());

        Cityarea simArea = cservice.getMostSimilarArea(simCity.getId(), model.getAreaName());
        if (simArea != null) {
          record.setAreaId(simArea.getId());
          record.setAreaName(simArea.getName());
        } else
          logger.error("无法找到归并地区：" + model.getCityName() + " - " + model.getAreaName());
      } else
        logger.error("无法找到归并城市：" + model.getCityName());


      service.insertOrUpate(record);
    }

  }
}
