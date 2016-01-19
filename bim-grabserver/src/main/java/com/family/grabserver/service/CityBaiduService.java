package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CityBaidu;
import com.family.grabserver.mapper.bim_grab.CityBaiduMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityBaiduService {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CityBaiduMapper mapper;

  public List<CityBaidu> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpate(CityBaidu record) {
    try {
      if (record.getId() == null)
        return mapper.insert(record);
      else {
        if (mapper.selectByPrimaryKey(record.getId()) != null)
          return mapper.updateByPrimaryKey(record);
        else
          return mapper.insert(record);
      }
    } catch (Exception e) {
      logger.info("百度 出现重复城市");
    }
    return -1;
  }
}

