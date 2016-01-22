package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CinemamovieMaoyan;
import com.family.grabserver.mapper.bim_grab.CinemamovieMaoyanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CinemamovieMaoyanService {
  @Autowired
  private CinemamovieMaoyanMapper mapper;

  public List<CinemamovieMaoyan> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(CinemamovieMaoyan record) {

    if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
    else return mapper.insert(record);
  }

}
