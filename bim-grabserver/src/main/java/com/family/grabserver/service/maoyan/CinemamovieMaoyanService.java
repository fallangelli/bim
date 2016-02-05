package com.family.grabserver.service.maoyan;

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

    if (record.getCinemaId() == null || record.getMovieId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getCinemaId(), record.getMovieId()) != null)
        return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
