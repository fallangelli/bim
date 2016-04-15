package com.family.grabserver.service.mtime;

import com.family.grabserver.entity.bim_grab.CityMtime;
import com.family.grabserver.mapper.bim_grab.CityMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityMtimeService {
  @Autowired
  private CityMtimeMapper mapper;

  public List<CityMtime> selectAll() {
    return mapper.selectAll();
  }

  public int deleteByPrimaryKey(Integer id) {
    return mapper.deleteByPrimaryKey(id);
  }

  public int insertOrUpdate(CityMtime record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

  public void deleteInvalidCity() {
    //删除 时光市
    mapper.deleteByPrimaryKey(5051);
  }


}
