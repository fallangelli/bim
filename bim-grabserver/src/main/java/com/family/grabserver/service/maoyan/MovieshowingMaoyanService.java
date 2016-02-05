package com.family.grabserver.service.maoyan;

import com.family.grabserver.entity.bim_grab.MovieshowingMaoyan;
import com.family.grabserver.mapper.bim_grab.MovieshowingMaoyanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class MovieshowingMaoyanService {
  @Autowired
  private MovieshowingMaoyanMapper mapper;

  public MovieshowingMaoyan selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpdate(MovieshowingMaoyan record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
