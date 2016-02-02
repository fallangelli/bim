package com.family.grabserver.service;

import com.family.grabserver.entity.bim_grab.CommentMtime;
import com.family.grabserver.mapper.bim_grab.CommentMtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CommentMtimeService {
  @Autowired
  private CommentMtimeMapper mapper;

  public List<CommentMtime> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(CommentMtime record) {
    if (record.getId() == null) {
      return mapper.insert(record);
    } else {
      if (mapper.selectByPrimaryKey(record.getId()) != null) return mapper.updateByPrimaryKey(record);
      else return mapper.insert(record);
    }
  }

}
