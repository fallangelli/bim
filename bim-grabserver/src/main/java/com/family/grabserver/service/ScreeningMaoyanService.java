package com.family.grabserver.service;

import com.family.grabserver.entity.ScreeningMaoyan;
import com.family.grabserver.mapper.ScreeningMaoyanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class ScreeningMaoyanService {
  @Autowired
  private ScreeningMaoyanMapper mapper;

  public int insertOrUpate(ScreeningMaoyan record) {
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
