package com.family.grabserver.service.weixin;

import com.family.grabserver.entity.bim_grab.CinemaWeixin;
import com.family.grabserver.mapper.bim_grab.CinemaWeixinMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CinemaWeixinService {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CinemaWeixinMapper mapper;

  public List<CinemaWeixin> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(CinemaWeixin record) {
    try {
      if (record.getId() == null)
        return mapper.insert(record);
      else {
        if (mapper.selectByPrimaryKey(record.getId()) != null)
          return mapper.updateByPrimaryKey(record);
        else
          return mapper.insert(record);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }
}

