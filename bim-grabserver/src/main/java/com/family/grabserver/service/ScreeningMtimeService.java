package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.ScreeningMtime;
import com.family.grabserver.mapper.bim_grab.ScreeningMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class ScreeningMtimeService {
  @Autowired
  private ScreeningMtimeMapper mapper;

  public int insertOrUpdate(ScreeningMtime record) {
    if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
    else return mapper.insert(record);
  }

}
