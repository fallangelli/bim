package com.family.grabserver.SolidifyUtil;

import com.family.grabserver.entity.bim_base.Cinema;
import com.family.grabserver.entity.bim_grab.CinemaBaidu;
import com.family.grabserver.entity.bim_grab.CinemaMtime;
import com.family.grabserver.mapper.bim_base.CinemaMapper;
import com.family.grabserver.service.CinemaBaiduService;
import com.family.grabserver.service.CinemaMtimeService;
import com.family.grabserver.util.Levenshtein;
import javafx.util.Pair;

import java.util.List;

public class CinemaSolidfier {

  public static void mergeMtimeCinema(CinemaMtimeService cmService, CinemaMapper caMapper) {
    List<CinemaMtime> cinemaMtimeList = cmService.selectAll();
    List<Cinema> cinemas = caMapper.selectAll();

    for (CinemaMtime cm : cinemaMtimeList) {
      if (cm.getAreaId() == null || cm.getAreaId() < 0)
        continue;

      boolean isMatched = false;
      for (Cinema record : cinemas) {
        if (isMatched(new Pair<>(cm.getName(), record.getName()),
          new Pair<>(cm.getAddress(), record.getAddress()))) {
          if (record.getAddress().length() < cm.getAddress().length()) {
            record.setAddress(cm.getAddress());
          }
          if (record.getLatitude() == null || record.getLatitude().length() <= 0) {
            record.setLatitude(cm.getLatitude());
          }
          if (record.getLongitude() == null || record.getLongitude().length() <= 0) {
            record.setLongitude(cm.getLongitude());
          }
          if (record.getRating() == null || record.getRating() <= 0) {
            record.setRating(cm.getRating());
          }
          if (record.getHas3d() == null) {
            record.setHas3d(cm.getHas3d());
          }
          if (record.getHasImax() == null) {
            record.setHasImax(cm.getHasImax());
          }
          if (record.getHasWifi() == null) {
            record.setHasWifi(cm.getHasWifi());
          }
          if (record.getHasPark() == null) {
            record.setHasPark(cm.getHasPark());
          }
          if (record.getTel() == null) {
            record.setTel(cm.getTel());
          }
          if (record.getRoute() == null) {
            record.setRoute(cm.getRoute());
          }
          record.setIdMtime(cm.getId());
          caMapper.updateByPrimaryKey(record);
          isMatched = true;
          break;
        }
      }
      if (!isMatched) {
        Cinema record = new Cinema();
        record.setDistrictId(cm.getAreaId());
        record.setName(cm.getName());
        record.setAddress(cm.getAddress());
        record.setLatitude(cm.getLatitude());
        record.setLongitude(cm.getLongitude());
        record.setRating(cm.getRating());
        record.setHas3d(cm.getHas3d());
        record.setHasImax(cm.getHasImax());
        record.setHasWifi(cm.getHasWifi());
        record.setHasPark(cm.getHasPark());
        record.setTel(cm.getTel());
        record.setRoute(cm.getRoute());
        record.setIdMtime(cm.getId());
        if (caMapper.selectByPrimaryKey(record.getId()) != null)
          caMapper.updateByPrimaryKey(record);
        else
          caMapper.insert(record);
      }
    }
  }


  public static void mergeBaiduCinema(CinemaBaiduService cbService, CinemaMapper caMapper) {
    List<CinemaBaidu> cinemaBaiduList = cbService.selectAll();
    List<Cinema> baseCinemas = caMapper.selectAll();

    for (CinemaBaidu cm : cinemaBaiduList) {
      if (cm.getAreaId() == null || cm.getAreaId() < 0)
        continue;

      boolean isMatched = false;
      for (Cinema record : baseCinemas) {
        if (isMatched(new Pair<>(cm.getName(), record.getName()),
          new Pair<>(cm.getAddress(), record.getAddress()))) {
          if (record.getAddress().length() < cm.getAddress().length()) {
            record.setAddress(cm.getAddress());
          }
          record.setIdBaidu(cm.getId());
          caMapper.updateByPrimaryKey(record);
          isMatched = true;
          break;
        }
      }

      if (!isMatched) {
        Cinema record = new Cinema();
        record.setDistrictId(cm.getAreaId());
        record.setName(cm.getName());
        record.setAddress(cm.getAddress());
        record.setIdBaidu(cm.getId());
        if (caMapper.selectByPrimaryKey(record.getId()) != null)
          caMapper.updateByPrimaryKey(record);
        else
          caMapper.insert(record);
      }
    }
  }


  private static Boolean isMatched(Pair<String, String> namePair, Pair<String, String> addressPair) {
    String nameA = namePair.getKey();
    String nameB = namePair.getValue();
    if (Levenshtein.getSimilarityRatio(nameA, nameB) > 0.8) {
      return true;
    } else {
      String addressA = addressPair.getKey();
      String addressB = addressPair.getValue();
      if (Levenshtein.getSimilarityRatio(addressA, addressB) > 0.5) {
        return true;
      }
    }

    return false;
  }

}
