package com.family.grabserver.service;

import com.family.grabserver.mapper.SolidifyMapper;
import com.family.grabserver.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/12/8.
 */
@SuppressWarnings("ALL")
@Service
public class MergeService {
  @Autowired
  private SolidifyMapper mapper;
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public void merge() {
    logger.info("开始执行合并");
    try {
      SqlUtil.deleteAll("bim_base.screening");
      SqlUtil.deleteAll("bim_base.movieshowing");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    mapper.merge_city_maoyan();
    mapper.merge_city_mtime();

    mapper.merge_city_area_mtime();
    mapper.merge_city_area_maoyan();

    mapper.merge_movieshowing_mtime();
    mapper.merge_movieshowing_maoyan();

    mapper.merge_cinema_mtime();
    mapper.merge_cinema_maoyan();

    mapper.merge_screening_maoyan();
    mapper.merge_screening_mtime();
  }

}
