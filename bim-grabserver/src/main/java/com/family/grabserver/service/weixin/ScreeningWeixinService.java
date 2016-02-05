package com.family.grabserver.service.weixin;

import com.family.grabserver.entity.bim_grab.ScreeningWeixin;
import com.family.grabserver.mapper.bim_grab.ScreeningWeixinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class ScreeningWeixinService {
  @Autowired
  private ScreeningWeixinMapper mapper;

  public List<ScreeningWeixin> selectAll() {
    return mapper.selectAll();
  }


  public int insert(ScreeningWeixin record) {
    return mapper.insert(record);
  }

}
