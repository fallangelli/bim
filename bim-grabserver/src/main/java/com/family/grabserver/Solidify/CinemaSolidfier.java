package com.family.grabserver.Solidify;

import com.family.grabserver.entity.bim_base.Cinema;
import com.family.grabserver.entity.bim_grab.CinemaBaidu;
import com.family.grabserver.entity.bim_grab.CinemaMaoyan;
import com.family.grabserver.entity.bim_grab.CinemaMtime;
import com.family.grabserver.entity.bim_grab.CinemaWeixin;
import com.family.grabserver.mapper.bim_base.CinemaMapper;
import com.family.grabserver.service.baidu.CinemaBaiduService;
import com.family.grabserver.service.maoyan.CinemaMaoyanService;
import com.family.grabserver.service.mtime.CinemaMtimeService;
import com.family.grabserver.service.weixin.CinemaWeixinService;
import com.family.grabserver.util.Cn2Spell;
import com.family.grabserver.util.Levenshtein;
import javafx.util.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CinemaSolidfier {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CinemaWeixinService wxService = applicationContext.getBean(CinemaWeixinService.class);
    final CinemaMapper caMapper = applicationContext.getBean(CinemaMapper.class);

    CinemaSolidfier.mergeWeixinCinema(wxService, caMapper);
  }

  public static void mergeWeixinCinema(CinemaWeixinService wxService, CinemaMapper caMapper) {
    List<CinemaWeixin> cinemaWeixinList = wxService.selectAll();
    List<Cinema> baseCinemas = caMapper.selectAll();

    for (CinemaWeixin cm : cinemaWeixinList) {
      if (cm.getAreaId() == null || cm.getAreaId() < 0)
        continue;

      boolean isMatched = false;
      for (Cinema record : baseCinemas) {
        if (isMatched(new Pair<>(cm.getName(), record.getName()),
          new Pair<>(cm.getAddress(), record.getAddress()))) {
          if (record.getAddress().length() < cm.getAddress().length()) {
            record.setAddress(cm.getAddress());
          }
          if (record.getLatitude() == null || record.getLatitude().length() <= 0
            || record.getLatitude().compareTo("0") == 0) {
            if (cm.getLatitude() != null && cm.getLatitude().length() > 0
              && cm.getLatitude().compareTo("0") != 0)
              record.setLatitude(cm.getLatitude());
            else
              record.setLongitude(null);
          }
          if (record.getLongitude() == null || record.getLongitude().length() <= 0
            || record.getLongitude().compareTo("0") == 0) {
            if (cm.getLongitude() != null && cm.getLongitude().length() > 0
              && cm.getLongitude().compareTo("0") != 0)
              record.setLongitude(cm.getLongitude());
            else
              record.setLongitude(null);
          }
          if (record.getTel() == null) {
            record.setTel(cm.getTel());
          }
          record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
          record.setIdWeixin(cm.getId());
          try {
            caMapper.updateByPrimaryKey(record);
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
        Cinema record = new Cinema();
        record.setDistrictId(cm.getAreaId());
        record.setName(cm.getName());
        record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
        record.setAddress(cm.getAddress());
        if (cm.getLatitude() != null && cm.getLatitude().length() > 0
          && cm.getLatitude().compareTo("0") != 0)
          record.setLatitude(cm.getLatitude());
        else
          record.setLongitude(null);
        if (cm.getLongitude() != null && cm.getLongitude().length() > 0
          && cm.getLongitude().compareTo("0") != 0)
          record.setLongitude(cm.getLongitude());
        else
          record.setLongitude(null);
        record.setTel(cm.getTel());
        record.setIdBaidu(cm.getId());
        try {
          if (caMapper.selectByPrimaryKey(record.getId()) != null)
            caMapper.updateByPrimaryKey(record);
          else
            caMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }
      }
    }
  }

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
          record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
          if (record.getAddress().length() < cm.getAddress().length()) {
            record.setAddress(cm.getAddress());
          }
          if (record.getLatitude() == null || record.getLatitude().length() <= 0
            || record.getLatitude().compareTo("0") == 0) {
            if (cm.getLatitude() != null && cm.getLatitude().length() > 0
              && cm.getLatitude().compareTo("0") != 0)
              record.setLatitude(cm.getLatitude());
            else
              record.setLongitude(null);
          }
          if (record.getLongitude() == null || record.getLongitude().length() <= 0
            || record.getLongitude().compareTo("0") == 0) {
            if (cm.getLongitude() != null && cm.getLongitude().length() > 0
              && cm.getLongitude().compareTo("0") != 0)
              record.setLongitude(cm.getLongitude());
            else
              record.setLongitude(null);
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
          try {
            caMapper.updateByPrimaryKey(record);
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
        Cinema record = new Cinema();
        record.setDistrictId(cm.getAreaId());
        record.setName(cm.getName());
        record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
        record.setAddress(cm.getAddress());
        if (cm.getLatitude() != null && cm.getLatitude().length() > 0
          && cm.getLatitude().compareTo("0") != 0)
          record.setLatitude(cm.getLatitude());
        else
          record.setLongitude(null);
        if (cm.getLongitude() != null && cm.getLongitude().length() > 0
          && cm.getLongitude().compareTo("0") != 0)
          record.setLongitude(cm.getLongitude());
        else
          record.setLongitude(null);
        record.setRating(cm.getRating());
        record.setHas3d(cm.getHas3d());
        record.setHasImax(cm.getHasImax());
        record.setHasWifi(cm.getHasWifi());
        record.setHasPark(cm.getHasPark());
        record.setTel(cm.getTel());
        record.setRoute(cm.getRoute());
        record.setIdMtime(cm.getId());
        try {
          if (caMapper.selectByPrimaryKey(record.getId()) != null)
            caMapper.updateByPrimaryKey(record);
          else
            caMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }
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
          record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
          record.setIdBaidu(cm.getId());
          try {
            caMapper.updateByPrimaryKey(record);
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
        Cinema record = new Cinema();
        record.setDistrictId(cm.getAreaId());
        record.setName(cm.getName());
        record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
        record.setAddress(cm.getAddress());
        record.setIdBaidu(cm.getId());
        try {
          if (caMapper.selectByPrimaryKey(record.getId()) != null)
            caMapper.updateByPrimaryKey(record);
          else
            caMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }
      }
    }
  }

  public static void mergeMaoyanCinema(CinemaMaoyanService cmyService, CinemaMapper caMapper) {
    List<CinemaMaoyan> cinemaMaoyanList = cmyService.selectAll();
    List<Cinema> cinemas = caMapper.selectAll();

    for (CinemaMaoyan cm : cinemaMaoyanList) {
      if (cm.getAreaId() == null || cm.getAreaId() < 0)
        continue;

      boolean isMatched = false;
      for (Cinema record : cinemas) {
        if (isMatched(new Pair<>(cm.getName(), record.getName()),
          new Pair<>(cm.getAddress(), record.getAddress()))) {
          record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
          if (record.getAddress().length() < cm.getAddress().length()) {
            record.setAddress(cm.getAddress());
          }
          record.setIdMaoyan(cm.getId());
          try {
            caMapper.updateByPrimaryKey(record);
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
        Cinema record = new Cinema();
        record.setDistrictId(cm.getAreaId());
        record.setName(cm.getName());
        record.setFirstspell(Cn2Spell.converterToFirstSpell(record.getName()));
        record.setAddress(cm.getAddress());
        record.setLongitude(null);
        record.setIdMaoyan(cm.getId());
        try {
          if (caMapper.selectByPrimaryKey(record.getId()) != null)
            caMapper.updateByPrimaryKey(record);
          else
            caMapper.insert(record);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }
      }
    }
  }

  private static Boolean isMatched(Pair<String, String> namePair, Pair<String, String> addressPair) {
    String nameA = namePair.getKey();
    String nameB = namePair.getValue();

    String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(nameA);
    nameA = m.replaceAll("").trim();
    nameA = nameA.toUpperCase();
    m = p.matcher(nameB);
    nameB = m.replaceAll("").trim();
    nameB = nameB.toUpperCase();

    if (nameA.length() > 0 && nameB.length() > 0 &&
      Levenshtein.getSimilarityRatio(nameA, nameB) > 0.9) {
      return true;
    } else {
      String addressA = addressPair.getKey();
      String addressB = addressPair.getValue();
      if (addressA.length() > 0 && addressB.length() > 0 &&
        Levenshtein.getSimilarityRatio(addressA, addressB) > 0.5) {
        return true;
      }
    }

    return false;
  }

}
