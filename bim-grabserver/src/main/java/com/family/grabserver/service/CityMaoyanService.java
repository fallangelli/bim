package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CityMaoyan;
import com.family.grabserver.mapper.bim_grab.CityMaoyanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityMaoyanService {
  @Autowired
  private CityMaoyanMapper mapper;

  public List<CityMaoyan> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(CityMaoyan record) {
    if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
    else return mapper.insert(record);
  }

}
