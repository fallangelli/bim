package com.family.grabserver.service.mtime;

import com.family.grabserver.entity.bim_grab.MovieshowingMtime;
import com.family.grabserver.mapper.bim_grab.MovieshowingMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class MovieshowingMtimeService {
  @Autowired
  private MovieshowingMtimeMapper mapper;

  public List<MovieshowingMtime> selectAll() {
    return mapper.selectAll();
  }

  public MovieshowingMtime selectByPrimaryKey(Integer id) {
    return mapper.selectByPrimaryKey(id);
  }

  public int insertOrUpdate(MovieshowingMtime record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null)
        return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
