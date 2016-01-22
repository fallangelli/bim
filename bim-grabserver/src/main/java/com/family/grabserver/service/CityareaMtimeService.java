package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CityareaMtime;
import com.family.grabserver.mapper.bim_grab.CityareaMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class CityareaMtimeService {
  @Autowired
  private CityareaMtimeMapper mapper;

  public CityareaMtime selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpdate(CityareaMtime record) {
    if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
    else return mapper.insert(record);
  }

}
