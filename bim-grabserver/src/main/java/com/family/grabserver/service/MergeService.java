package com.family.grabserver.service;

import com.family.grabserver.mapper.SolidifyMapper;
import com.family.grabserver.util.SqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/12/8.
 */
@SuppressWarnings("ALL")
@Service
public class MergeService {
  @Autowired
  private SolidifyMapper mapper;
  @Autowired
  private CityMaoyanService cityServiceMaoyan;

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final MergeService service = applicationContext.getBean(MergeService.class);
    service.merge();
  }

  public void merge() {
    logger.info("开始执行合并");
    try {
      SqlUtil.truncateTable("bim_base.screening");
      SqlUtil.truncateTable("bim_base.movieshowing");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
    mapper.merge_city_mtime();
//    List<CityMaoyan> cityMaoyanList = csMaoyan.selectAll();
//    for(CityMaoyan city : cityMaoyanList){
//      if(Levenshtein.participle(city.getName(),))
//    }
//
//
//    mapper.merge_city_maoyan();
//
//    mapper.merge_city_baidu();
//
//    mapper.merge_city_area_maoyan();
//    mapper.merge_city_area_mtime();
//    mapper.merge_city_area_baidu();
//
//    mapper.merge_movieshowing_maoyan();
//    mapper.merge_movieshowing_mtime();
//    mapper.merge_movieshowing_baidu();
//
//    mapper.merge_cinema_maoyan();
    mapper.merge_cinema_mtime();
//    mapper.merge_cinema_baidu();
//
//    mapper.merge_screening_maoyan();
//    mapper.merge_screening_mtime();
//    mapper.merge_screening_baidu();
  }

}
