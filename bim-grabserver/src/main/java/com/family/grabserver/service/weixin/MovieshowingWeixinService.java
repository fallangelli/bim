package com.family.grabserver.service.weixin;

import com.family.grabserver.entity.bim_grab.MovieshowingWeixin;
import com.family.grabserver.mapper.bim_grab.MovieshowingWeixinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class MovieshowingWeixinService {
  @Autowired
  private MovieshowingWeixinMapper mapper;

  public List<MovieshowingWeixin> selectAll() {
    return mapper.selectAll();
  }

  public MovieshowingWeixin selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpdate(MovieshowingWeixin record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
