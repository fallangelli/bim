package com.family.grabserver.service;

import com.family.grabserver.entity.ScreeningBaidu;
import com.family.grabserver.mapper.ScreeningBaiduMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class ScreeningBaiduService {
  @Autowired
  private ScreeningBaiduMapper mapper;

  public int insertOrUpate(ScreeningBaidu record) {
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
