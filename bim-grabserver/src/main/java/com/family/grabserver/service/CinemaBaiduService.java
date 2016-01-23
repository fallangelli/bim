package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CinemaBaidu;
import com.family.grabserver.mapper.bim_grab.CinemaBaiduMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CinemaBaiduService {
  @Autowired
  private CinemaBaiduMapper mapper;

  public List<CinemaBaidu> selectAll() {
    return mapper.selectAll();
  }

  public CinemaBaidu selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpdate(CinemaBaidu record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
