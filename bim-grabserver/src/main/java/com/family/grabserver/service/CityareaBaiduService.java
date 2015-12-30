package com.family.grabserver.service;

import com.family.grabserver.entity.CityareaBaidu;
import com.family.grabserver.mapper.CityareaBaiduMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityareaBaiduService {
  @Autowired
  private CityareaBaiduMapper mapper;

  public CityareaBaidu selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpate(CityareaBaidu record) {
    if (record.getId() == null)
      return mapper.insert(record);
    else {
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return mapper.updateByPrimaryKey(record);
      else
        return mapper.insert(record);
    }
  }

  public List<CityareaBaidu> selectAll() {
    return mapper.selectAll();
  }

}
