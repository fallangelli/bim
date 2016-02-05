package com.family.grabserver.service;

import com.family.grabserver.mapper.bim_base.*;
import com.family.grabserver.mapper.bim_grab.CityMtimeMapper;
import com.family.grabserver.service.baidu.CinemaBaiduService;
import com.family.grabserver.service.baidu.MovieshowingBaiduService;
import com.family.grabserver.service.mtime.CinemaMtimeService;
import com.family.grabserver.service.mtime.CommentMtimeService;
import com.family.grabserver.service.mtime.MovieshowingMtimeService;
import com.family.grabserver.service.weixin.CinemaWeixinService;
import com.family.grabserver.service.weixin.MovieshowingWeixinService;
import com.family.grabserver.util.SqlUtil;
import com.family.webserver.utils.EnumSource;
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
  private CinemaWeixinService wxService;

  @Autowired
  private MovieshowingMtimeService mmService;
  @Autowired
  private MovieshowingBaiduService mbService;
  @Autowired
  private MovieshowingWeixinService mwService;


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
      SqlUtil.truncateTable("bim_base.screening");
      SqlUtil.truncateTable("bim_base.comment");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

//    //删除 时光市
//    cmMapper.deleteByPrimaryKey(5051);
//
//    logger.info("开始执行合并 - 影院");
//
//    CinemaSolidfier.mergeMtimeCinema(cmService, caMapper);
//    CinemaSolidfier.mergeBaiduCinema(cbService, caMapper);
//    CinemaSolidfier.mergeWeixinCinema(wxService, caMapper);
//
//    mapper.fillPositions();
//
//    logger.info("开始执行合并 - 电影");
//    MovieSolidfier.mergeMtimeMovieshowing(mmService, mMapper);
//    MovieSolidfier.mergeBaiduMovieshowing(mbService, mMapper);
//    MovieSolidfier.mergeWeixinMovieshowing(mwService, mMapper);

    mapper.merge_comment_mtime(EnumSource.MTIME.getCode());

    mapper.merge_image_mtime(EnumSource.MTIME.getCode());

    logger.info("开始执行合并 - 放映");
    mapper.merge_screening_mtime(EnumSource.MTIME.getCode());
    mapper.merge_screening_baidu(EnumSource.BAIDU.getCode());
    mapper.merge_screening_weixin(EnumSource.WEIXIN.getCode());

  }

}
