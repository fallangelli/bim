package com.family.grabserver.service;

import com.family.grabserver.SolidifyUtil.CinemaSolidfier;
import com.family.grabserver.mapper.bim_base.CinemaMapper;
import com.family.grabserver.mapper.bim_base.CityMapper;
import com.family.grabserver.mapper.bim_grab.SolidifyMapper;
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
public class SolidifyService {
  @Autowired
  private SolidifyMapper mapper;
  @Autowired
  private CityMapper cMapper;
  @Autowired
  private CinemaBaiduService cbService;
  @Autowired
  private CinemaMtimeService cmService;
  @Autowired
  private CinemaMapper caMapper;


  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final SolidifyService service = applicationContext.getBean(SolidifyService.class);
    service.merge();
  }

  public void merge() {
    logger.info("开始执行合并");
//    CinemaSolidfier.mergeMtimeCinema(cmService, caMapper);
    CinemaSolidfier.mergeBaiduCinema(cbService, caMapper);

//    try {
//      SqlUtil.truncateTable("bim_base.screening");
//      SqlUtil.truncateTable("bim_base.movieshowing");
//    } catch (Exception e) {
//      e.printStackTrace();
//      return;
//    }
//    mapper.merge_city_mtime();
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
//    mapper.merge_cinema_mtime();
//    mapper.merge_cinema_baidu();
//
//    mapper.merge_screening_maoyan();
//    mapper.merge_screening_mtime();
//    mapper.merge_screening_baidu();
  }

}
