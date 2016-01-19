package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.MovieshowingMtime;
import com.family.grabserver.mapper.bim_grab.MovieshowingMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class MovieshowingMtimeService {
  @Autowired
  private MovieshowingMtimeMapper mapper;

  public MovieshowingMtime selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpate(MovieshowingMtime record) {
    if (record.getId() == null)
      return mapper.insert(record);
    else {
      //电影信息不覆盖
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return 0;
      else
        return mapper.insert(record);
    }
  }

}
