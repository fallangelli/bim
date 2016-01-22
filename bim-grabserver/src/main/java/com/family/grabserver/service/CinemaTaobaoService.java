package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CinemaTaobao;
import com.family.grabserver.mapper.bim_grab.CinemaTaobaoMapper;
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

  public int insertOrUpdate(CinemaTaobao record) {
    if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
    else return mapper.insert(record);
  }

}
