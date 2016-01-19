package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CityTaobao;
import com.family.grabserver.mapper.bim_grab.CityTaobaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityTaobaoService {
  @Autowired
  private CityTaobaoMapper mapper;

  public List<CityTaobao> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpate(CityTaobao record) {
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
