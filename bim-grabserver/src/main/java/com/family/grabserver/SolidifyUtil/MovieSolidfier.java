package com.family.grabserver.SolidifyUtil;

import com.family.grabserver.entity.bim_base.Movieshowing;
import com.family.grabserver.entity.bim_grab.MovieshowingBaidu;
import com.family.grabserver.entity.bim_grab.MovieshowingMtime;
import com.family.grabserver.mapper.bim_base.MovieshowingMapper;
import com.family.grabserver.service.MovieshowingBaiduService;
import com.family.grabserver.service.MovieshowingMtimeService;

import java.util.List;

public class MovieSolidfier {

  public static void mergeMtimeMovieshowing(MovieshowingMtimeService mmService, MovieshowingMapper mMapper) {
    List<MovieshowingMtime> MovieshowingMtimeList = mmService.selectAll();
    List<Movieshowing> Movieshowings = mMapper.selectAll();

    for (MovieshowingMtime cm : MovieshowingMtimeList) {

      boolean isMatched = false;
      for (Movieshowing record : Movieshowings) {
        if (cm.getName().compareTo(record.getName()) == 0) {
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
          mMapper.updateByPrimaryKey(record);
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
        record.setRating(cm.getRating());
        record.setActors(cm.getActors());
        record.setType(cm.getType());
        record.setContent(cm.getContent());
        record.setRuntime(cm.getRuntime());
        record.setIdMtime(cm.getId());
        if (mMapper.selectByPrimaryKey(record.getId()) != null)
          mMapper.updateByPrimaryKey(record);
        else
          mMapper.insert(record);
      }
    }
  }


  public static void mergeBaiduMovieshowing(MovieshowingBaiduService cbService, MovieshowingMapper mMapper) {
    List<MovieshowingBaidu> MovieshowingBaiduList = cbService.selectAll();
    List<Movieshowing> baseMovieshowings = mMapper.selectAll();

    for (MovieshowingBaidu cm : MovieshowingBaiduList) {
      boolean isMatched = false;
      for (Movieshowing record : baseMovieshowings) {
        if (cm.getName().compareTo(record.getName()) == 0) {
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
          mMapper.updateByPrimaryKey(record);
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
        if (mMapper.selectByPrimaryKey(record.getId()) != null)
          mMapper.updateByPrimaryKey(record);
        else
          mMapper.insert(record);
      }
    }
  }


}