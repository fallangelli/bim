package com.family.grabserver.service;

import com.family.grabserver.entity.CityBaidu;
import com.family.grabserver.mapper.CityBaiduMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityBaiduService {
  @Autowired
  private CityBaiduMapper mapper;

  public List<CityBaidu> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpate(CityBaidu record) {
    if (record.getId() == null)
      return mapper.insert(record);
    else {
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return mapper.updateByPrimaryKey(record);
      else
        return mapper.insert(record);
    }
  }

}
