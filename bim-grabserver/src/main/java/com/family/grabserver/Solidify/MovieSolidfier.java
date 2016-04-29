package com.family.grabserver.Solidify;

import com.family.grabserver.entity.bim_base.Movieshowing;
import com.family.grabserver.entity.bim_grab.MovieshowingBaidu;
import com.family.grabserver.entity.bim_grab.MovieshowingMaoyan;
import com.family.grabserver.entity.bim_grab.MovieshowingMtime;
import com.family.grabserver.entity.bim_grab.MovieshowingWeixin;
import com.family.grabserver.mapper.bim_base.MovieshowingMapper;
import com.family.grabserver.service.baidu.MovieshowingBaiduService;
import com.family.grabserver.service.maoyan.MovieshowingMaoyanService;
import com.family.grabserver.service.mtime.MovieshowingMtimeService;
import com.family.grabserver.service.weixin.MovieshowingWeixinService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MovieSolidfier {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final MovieshowingWeixinService wxService = applicationContext.getBean(MovieshowingWeixinService.class);
    final MovieshowingMapper mMapper = applicationContext.getBean(MovieshowingMapper.class);

    MovieSolidfier.mergeWeixinMovieshowing(wxService, mMapper);
  }


  public static void mergeWeixinMovieshowing(MovieshowingWeixinService wxService, MovieshowingMapper mMapper) {
    List<MovieshowingWeixin> MovieshowingWeixinList = wxService.selectAll();
    List<Movieshowing> baseMovieshowings = mMapper.selectAll();

    for (MovieshowingWeixin mWeixin : MovieshowingWeixinList) {
      boolean isMatched = false;
      for (Movieshowing record : baseMovieshowings) {
        if (isMatched(mWeixin.getName(), record.getName())) {
          if (record.getImage() == null || record.getImage().length() <= 0) {
            record.setImage(mWeixin.getImage());
          }
          if (record.getRating() == null || record.getRating() <= 0) {
            record.setRating(mWeixin.getRating());
          }
          if (record.getDirectors() == null || record.getDirectors().length() <= 0) {
            record.setDirectors(mWeixin.getDirectors());
          }
          if (record.getActors() == null || record.getActors().length() <= 0) {
            record.setActors(mWeixin.getActors());
          }
          if (record.getType() == null || record.getType().length() <= 0) {
            record.setType(mWeixin.getType());
          }
          if (record.getContent() == null || record.getContent().length() <= 0) {
            record.setContent(mWeixin.getContent());
          }
          if (record.getRuntime() == null || record.getRuntime().length() <= 0) {
            record.setRuntime(mWeixin.getRuntime());
          }
          if (record.getVer() == null || record.getVer().length() <= 0) {
            record.setRuntime(mWeixin.getVer());
          }
          record.setIdWeixin(mWeixin.getId());
          try {
            mMapper.updateByPrimaryKey(record);
          } catch (Exception e) {
            e.printStackTrace();
            isMatched = true;
            break;
          }
          isMatched = true;
          break;
        }
      }

      if (!isMatched) {
        Movieshowing record = new Movieshowing();
        record.setName(mWeixin.getName());
        record.setImage(mWeixin.getImage());
        record.setRating(mWeixin.getRating());
        record.setDirectors(mWeixin.getDirectors());
        record.setActors(mWeixin.getActors());
        record.setType(mWeixin.getType());
        record.setContent(mWeixin.getContent());
        record.setRuntime(mWeixin.getRuntime());
        record.setVer(mWeixin.getVer());
        record.setIdWeixin(mWeixin.getId());
        try {
          if (mMapper.selectByPrimaryKey(record.getId()) != null)
            mMapper.updateByPrimaryKey(record);
          else
            mMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }

      }
    }
  }

  public static void mergeMtimeMovieshowing(MovieshowingMtimeService mmService, MovieshowingMapper mMapper) {
    List<MovieshowingMtime> MovieshowingMtimeList = mmService.selectAll();
    List<Movieshowing> Movieshowings = mMapper.selectAll();

    for (MovieshowingMtime cm : MovieshowingMtimeList) {

      boolean isMatched = false;
      for (Movieshowing record : Movieshowings) {
        if (isMatched(cm.getName(), record.getName())) {
          if (record.getImage() == null || record.getImage().length() <= 0) {
            record.setImage(cm.getImage());
          }
          if (record.getRating() == null || record.getRating() <= 0) {
            record.setRating(cm.getRating());
          }
          if (record.getIs3d() == null) {
            record.setIs3d(cm.getIs3d());
          }
          if (record.getIsImax() == null) {
            record.setIsImax(cm.getIsImax());
          }
          if (record.getDirectors() == null || record.getDirectors().length() <= 0) {
            record.setDirectors(cm.getDirectors());
          }
          if (record.getActors() == null || record.getActors().length() <= 0) {
            record.setActors(cm.getActors());
          }
          if (record.getType() == null || record.getType().length() <= 0) {
            record.setType(cm.getType());
          }
          if (record.getContent() == null || record.getContent().length() <= 0) {
            record.setContent(cm.getContent());
          }
          if (record.getRuntime() == null || record.getRuntime().length() <= 0) {
            record.setRuntime(cm.getRuntime());
          }
          record.setIdMtime(cm.getId());
          try {
            mMapper.updateByPrimaryKey(record);
          } catch (Exception e) {
            e.printStackTrace();
            isMatched = true;
            break;
          }

          isMatched = true;
          break;
        }
      }
      if (!isMatched) {
        Movieshowing record = new Movieshowing();
        record.setName(cm.getName());
        record.setImage(cm.getImage());
        record.setRating(cm.getRating());
        record.setIs3d(cm.getIs3d());
        record.setIsImax(cm.getIsImax());
        record.setDirectors(cm.getDirectors());
        record.setActors(cm.getActors());
        record.setType(cm.getType());
        record.setContent(cm.getContent());
        record.setRuntime(cm.getRuntime());
        record.setIdMtime(cm.getId());
        try {
          if (mMapper.selectByPrimaryKey(record.getId()) != null)
            mMapper.updateByPrimaryKey(record);
          else
            mMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }
      }
    }
  }


  public static void mergeBaiduMovieshowing(MovieshowingBaiduService cbService, MovieshowingMapper mMapper) {
    List<MovieshowingBaidu> MovieshowingBaiduList = cbService.selectAll();
    List<Movieshowing> baseMovieshowings = mMapper.selectAll();

    for (MovieshowingBaidu cm : MovieshowingBaiduList) {
      boolean isMatched = false;
      for (Movieshowing record : baseMovieshowings) {
        if (isMatched(cm.getName(), record.getName())) {
          if (record.getImage() == null || record.getImage().length() <= 0) {
            record.setImage(cm.getImage());
          }
          if (record.getRating() == null || record.getRating() <= 0) {
            record.setRating(cm.getRating());
          }
          if (record.getContent() == null || record.getContent().length() <= 0) {
            record.setContent(cm.getContent());
          }
          record.setIdBaidu(cm.getId());
          try {
            mMapper.updateByPrimaryKey(record);
          } catch (Exception e) {
            e.printStackTrace();
            isMatched = true;
            break;
          }
          isMatched = true;
          break;
        }
      }

      if (!isMatched) {
        Movieshowing record = new Movieshowing();
        record.setName(cm.getName());
        record.setImage(cm.getImage());
        record.setRating(cm.getRating());
        record.setContent(cm.getContent());
        record.setIdBaidu(cm.getId());
        try {
          if (mMapper.selectByPrimaryKey(record.getId()) != null)
            mMapper.updateByPrimaryKey(record);
          else
            mMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }

      }
    }
  }

  public static void mergeMaoyanMovieshowing(MovieshowingMaoyanService mmyService, MovieshowingMapper mMapper) {
    List<MovieshowingMaoyan> MovieshowingMaoyanList = mmyService.selectAll();
    List<Movieshowing> baseMovieshowings = mMapper.selectAll();

    for (MovieshowingMaoyan cm : MovieshowingMaoyanList) {
      boolean isMatched = false;
      for (Movieshowing record : baseMovieshowings) {
        if (isMatched(cm.getName(), record.getName())) {
          if (record.getImage() == null || record.getImage().length() <= 0) {
            record.setImage(cm.getImage());
          }
          if (record.getRating() == null || record.getRating() <= 0) {
            record.setRating(cm.getRating());
          }
          if (record.getIs3d() == null) {
            record.setIs3d(cm.getIs3d());
          }
          if (record.getIsImax() == null) {
            record.setIsImax(cm.getIsImax());
          }
          if (record.getDirectors() == null || record.getDirectors().length() <= 0) {
            record.setDirectors(cm.getDirectors());
          }
          if (record.getActors() == null || record.getActors().length() <= 0) {
            record.setActors(cm.getActors());
          }
          if (record.getType() == null || record.getType().length() <= 0) {
            record.setType(cm.getType());
          }
          if (record.getContent() == null || record.getContent().length() <= 0) {
            record.setContent(cm.getContent());
          }
          if (record.getRuntime() == null || record.getRuntime().length() <= 0) {
            record.setRuntime(cm.getRuntime());
          }
          if (record.getVer() == null || record.getVer().length() <= 0) {
            record.setVer(cm.getVer());
          }
          record.setIdMaoyan(cm.getId());
          try {
            mMapper.updateByPrimaryKey(record);
          } catch (Exception e) {
            e.printStackTrace();
            isMatched = true;
            break;
          }

          isMatched = true;
          break;
        }
      }
      if (!isMatched) {
        Movieshowing record = new Movieshowing();
        record.setName(cm.getName());
        record.setImage(cm.getImage());
        record.setRating(cm.getRating());
        record.setIs3d(cm.getIs3d());
        record.setIsImax(cm.getIsImax());
        record.setDirectors(cm.getDirectors());
        record.setActors(cm.getActors());
        record.setType(cm.getType());
        record.setContent(cm.getContent());
        record.setRuntime(cm.getRuntime());
        record.setVer(cm.getVer());
        record.setIdMaoyan(cm.getId());
        try {
          if (mMapper.selectByPrimaryKey(record.getId()) != null)
            mMapper.updateByPrimaryKey(record);
          else
            mMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }
      }
    }
  }

  private static Boolean isMatched(String nameA, String nameB) {
    if (nameA.length() > 0 && nameB.length() > 0 &&
      nameA.compareTo(nameB) == 0) {
      return true;
    }

    return false;
  }

}
