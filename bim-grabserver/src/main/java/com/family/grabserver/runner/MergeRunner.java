package com.family.grabserver.runner;

import com.family.grabserver.Solidify.CinemaSolidfier;
import com.family.grabserver.Solidify.MovieSolidfier;
import com.family.grabserver.mapper.bim_base.*;
import com.family.grabserver.mapper.bim_grab.CityMtimeMapper;
import com.family.grabserver.service.baidu.CinemaBaiduService;
import com.family.grabserver.service.baidu.MovieshowingBaiduService;
import com.family.grabserver.service.maoyan.CinemaMaoyanService;
import com.family.grabserver.service.maoyan.MovieshowingMaoyanService;
import com.family.grabserver.service.mtime.CinemaMtimeService;
import com.family.grabserver.service.mtime.CommentMtimeService;
import com.family.grabserver.service.mtime.MovieshowingMtimeService;
import com.family.grabserver.service.weixin.CinemaWeixinService;
import com.family.grabserver.service.weixin.MovieshowingWeixinService;
import com.family.grabserver.util.EnumType;
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
public class MergeRunner {
  @Autowired
  private SolidifyMapper mapper;

  @Autowired
  private CityMtimeMapper cmMapper;
  @Autowired
  private CinemaBaiduService cbdService;
  @Autowired
  private CinemaMtimeService cmtService;
  @Autowired
  private CinemaWeixinService cwxService;
  @Autowired
  private CinemaMaoyanService cmyService;

  @Autowired
  private MovieshowingMtimeService mmtService;
  @Autowired
  private MovieshowingBaiduService mbdService;
  @Autowired
  private MovieshowingWeixinService mwxService;
  @Autowired
  private MovieshowingMaoyanService mmyService;


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
    final MergeRunner service = applicationContext.getBean(MergeRunner.class);

    try {
      service.mergeCinema(4);
      service.mergeMovie(4);
//      service.mergeScreening(4);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void mergeCinema(int source) throws Exception {
    logger.info("开始合并影院");
    mapper.clear_u(EnumType.cinema.getName(), source);
    mapper.clear_d(EnumType.cinema.getName());
    switch (source) {
      case 0:
        CinemaSolidfier.mergeMtimeCinema(cmtService, caMapper);
        CinemaSolidfier.mergeBaiduCinema(cbdService, caMapper);
        CinemaSolidfier.mergeWeixinCinema(cwxService, caMapper);
        CinemaSolidfier.mergeMaoyanCinema(cmyService, caMapper);
        break;
      case 1:
        CinemaSolidfier.mergeMtimeCinema(cmtService, caMapper);
        break;
      case 2:
        CinemaSolidfier.mergeBaiduCinema(cbdService, caMapper);
        break;
      case 3:
        CinemaSolidfier.mergeMtimeCinema(cmtService, caMapper);
        break;
      case 4:
        CinemaSolidfier.mergeMaoyanCinema(cmyService, caMapper);
        break;
      default:
        throw new Exception("请选择源，0所有1时光2百度3微信4猫眼");
    }
  }


  public void mergeMovie(int source) throws Exception {
    logger.info("开始合并电影");
    mapper.clear_u(EnumType.movieshowing.getName(), source);
    mapper.clear_d(EnumType.movieshowing.getName());
    switch (source) {
      case 0:
        MovieSolidfier.mergeMtimeMovieshowing(mmtService, mMapper);
        MovieSolidfier.mergeBaiduMovieshowing(mbdService, mMapper);
        MovieSolidfier.mergeWeixinMovieshowing(mwxService, mMapper);
        MovieSolidfier.mergeMaoyanMovieshowing(mmyService, mMapper);

        mapper.merge_comment_mtime(EnumSource.MTIME.getCode());
        mapper.merge_image_mtime(EnumSource.MTIME.getCode());
        break;
      case 1:
        MovieSolidfier.mergeMtimeMovieshowing(mmtService, mMapper);
        mapper.merge_comment_mtime(EnumSource.MTIME.getCode());
        mapper.merge_image_mtime(EnumSource.MTIME.getCode());
        break;
      case 2:
        MovieSolidfier.mergeBaiduMovieshowing(mbdService, mMapper);
        break;
      case 3:
        MovieSolidfier.mergeWeixinMovieshowing(mwxService, mMapper);
        break;
      case 4:
        MovieSolidfier.mergeMaoyanMovieshowing(mmyService, mMapper);
        break;
      default:
        throw new Exception("请选择源，0所有1时光2百度3微信4猫眼");
    }
  }

  public void mergeScreening(int source) throws Exception {
    logger.info("开始合并上映信息");

    mapper.clear_screening(source);
    switch (source) {
      case 0:
        mapper.merge_screening_mtime(EnumSource.MTIME.getCode());
        mapper.merge_screening_baidu(EnumSource.BAIDU.getCode());
        mapper.merge_screening_weixin(EnumSource.WEIXIN.getCode());
        mapper.merge_screening_maoyan(EnumSource.MAOYAN.getCode());
        break;
      case 1:
        mapper.merge_screening_mtime(EnumSource.MTIME.getCode());
        break;
      case 2:
        mapper.merge_screening_baidu(EnumSource.BAIDU.getCode());
        break;
      case 3:
        mapper.merge_screening_weixin(EnumSource.WEIXIN.getCode());
        break;
      case 4:
        mapper.merge_screening_maoyan(EnumSource.MAOYAN.getCode());
        break;
      default:
        throw new Exception("请选择源，0所有1时光2百度3微信4猫眼");
    }
  }

  public void clearCinema(int source) {

  }
//
//  public void merge() {
//    logger.info("清除过期数据");
//    try {
//      SqlUtil.truncateTable("bim_base.screening");
//      SqlUtil.truncateTable("bim_base.comment");
//    } catch (Exception e) {
//      e.printStackTrace();
//      return;
//    }
//
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
//
//    mapper.merge_comment_mtime(EnumSource.MTIME.getCode());
//
//    mapper.merge_image_mtime(EnumSource.MTIME.getCode());
//
//    logger.info("开始执行合并 - 放映");
//    mapper.merge_screening_mtime(EnumSource.MTIME.getCode());
//    mapper.merge_screening_baidu(EnumSource.BAIDU.getCode());
//    mapper.merge_screening_weixin(EnumSource.WEIXIN.getCode());
//
//  }

}
