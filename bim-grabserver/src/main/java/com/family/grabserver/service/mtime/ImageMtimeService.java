package com.family.grabserver.service.mtime;

import com.family.grabserver.entity.bim_grab.ImageMtime;
import com.family.grabserver.mapper.bim_grab.ImageMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class ImageMtimeService {
  @Autowired
  private ImageMtimeMapper mapper;

  public List<ImageMtime> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(ImageMtime record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
