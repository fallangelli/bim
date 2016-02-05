package com.family.grabserver.service.maoyan;


import com.family.grabserver.entity.bim_grab.CinemaMaoyan;
import com.family.grabserver.mapper.bim_grab.CinemaMaoyanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CinemaMaoyanService {
  @Autowired
  private CinemaMaoyanMapper mapper;

  public List<CinemaMaoyan> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(CinemaMaoyan record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
