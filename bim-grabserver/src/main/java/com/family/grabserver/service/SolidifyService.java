package com.family.grabserver.service;

import com.family.grabserver.mapper.bim_base.*;
import com.family.grabserver.mapper.bim_grab.CityMtimeMapper;
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
public class SolidifyService {
  @Autowired
  private SolidifyMapper mapper;

  @Autowired
  private CityMtimeMapper cmMapper;
  @Autowired
  private CinemaBaiduService cbService;
  @Autowired
  private CinemaMtimeService cmService;

  @Autowired
  private MovieshowingMtimeService mmService;
  @Autowired
  private MovieshowingBaiduService mbService;

  @Autowired
  private CommentMtimeService comService;


  @Autowired
  private CityMapper cMapper;
  @Autowired
  private CinemaMapper caMapper;
  @Autowired
  private MovieshowingMapper mMapper;
  @Autowired
  private CommentMapper coMapper;


  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final SolidifyService service = applicationContext.getBean(SolidifyService.class);


    service.merge();
  }

  public void merge() {
    logger.info("清除过期数据");
    try {
//      SqlUtil.truncateTable("bim_base.screening");
      SqlUtil.truncateTable("bim_base.comment");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    //删除 时光市
//    cmMapper.deleteByPrimaryKey(5051);
//
//    logger.info("开始执行合并");
//
//    CinemaSolidfier.mergeMtimeCinema(cmService, caMapper);
//    CinemaSolidfier.mergeBaiduCinema(cbService, caMapper);
//
//    mapper.fillPositions();
//
//    MovieSolidfier.mergeMtimeMovieshowing(mmService, mMapper);
//    MovieSolidfier.mergeBaiduMovieshowing(mbService, mMapper);

    mapper.merge_comment_mtime();
//
//    mapper.merge_screening_mtime();
//    mapper.merge_screening_baidu();

  }

}
