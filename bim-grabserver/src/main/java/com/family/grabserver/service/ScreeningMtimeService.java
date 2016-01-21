package com.family.grabserver.service;

import com.family.grabserver.entity.ScreeningMtime;
import com.family.grabserver.mapper.ScreeningMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class ScreeningMtimeService {
  @Autowired
  private ScreeningMtimeMapper mapper;

  public int insertOrUpate(ScreeningMtime record) {
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
