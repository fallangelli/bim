package com.family.grabserver.service;

import com.family.grabserver.entity.CinemaTaobao;
import com.family.grabserver.mapper.CinemaTaobaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CinemaTaobaoService {
  @Autowired
  private CinemaTaobaoMapper mapper;

  public List<CinemaTaobao> selectAll() {
    return mapper.selectAll();
  }

  public CinemaTaobao selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpate(CinemaTaobao record) {
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
