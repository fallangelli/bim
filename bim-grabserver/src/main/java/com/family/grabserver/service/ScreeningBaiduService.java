package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.ScreeningBaidu;
import com.family.grabserver.mapper.bim_grab.ScreeningBaiduMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class ScreeningBaiduService {
  @Autowired
  private ScreeningBaiduMapper mapper;

  public int insertOrUpdate(ScreeningBaidu record) {
    if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
    else return mapper.insert(record);
  }

}
