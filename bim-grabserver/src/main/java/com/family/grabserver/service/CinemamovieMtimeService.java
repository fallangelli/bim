package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CinemamovieMtime;
import com.family.grabserver.mapper.bim_grab.CinemamovieMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CinemamovieMtimeService {
  @Autowired
  private CinemamovieMtimeMapper mapper;

  public List<CinemamovieMtime> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(CinemamovieMtime record) {

    if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
    else return mapper.insert(record);
  }

}
