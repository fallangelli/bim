package com.family.grabserver.service.weixin;

import com.family.grabserver.entity.bim_grab.CityWeixin;
import com.family.grabserver.mapper.bim_grab.CityWeixinMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityWeixinService {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CityWeixinMapper mapper;

  public List<CityWeixin> selectAll() {
    return mapper.selectAll();
  }

  public int insertOrUpdate(CityWeixin record) {
    try {
      if (record.getId() == null)
        return mapper.insert(record);
      else {
        if (mapper.selectByPrimaryKey(record.getId()) != null)
          return mapper.updateByPrimaryKey(record);
        else
          return mapper.insert(record);
      }
    } catch (DuplicateKeyException de) {
      logger.warn("微信-城市 重复键值 - " + record.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }
}

