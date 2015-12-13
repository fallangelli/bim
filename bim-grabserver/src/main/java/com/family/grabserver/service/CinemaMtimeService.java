package com.family.grabserver.service;

import com.family.grabserver.entity.CinemaMtime;
import com.family.grabserver.mapper.CinemaMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CinemaMtimeService {
  @Autowired
  private CinemaMtimeMapper mapper;

  public List<CinemaMtime> selectAll() {
    return mapper.selectAll();
  }

  public CinemaMtime selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpate(CinemaMtime record) {
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
