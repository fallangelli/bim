package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.MovieshowingBaidu;
import com.family.grabserver.mapper.bim_grab.MovieshowingBaiduMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class MovieshowingBaiduService {
  @Autowired
  private MovieshowingBaiduMapper mapper;

  public List<MovieshowingBaidu> selectAll() {
    return mapper.selectAll();
  }

  public MovieshowingBaidu selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpdate(MovieshowingBaidu record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
