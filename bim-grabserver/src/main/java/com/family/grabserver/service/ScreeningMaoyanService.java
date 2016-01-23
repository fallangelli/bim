package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.ScreeningMaoyan;
import com.family.grabserver.mapper.bim_grab.ScreeningMaoyanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class ScreeningMaoyanService {
  @Autowired
  private ScreeningMaoyanMapper mapper;

  public int insert(ScreeningMaoyan record) {
    return mapper.insert(record);
  }

}
