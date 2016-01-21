package com.family.grabserver.service;

import com.family.grabserver.entity.City;
import com.family.grabserver.entity.Cityarea;
import com.family.grabserver.mapper.CityMapper;
import com.family.grabserver.mapper.CityareaMapper;
import com.family.grabserver.mapper.SolidifyMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityService {
  private static String[] KEY_WORDS = {"朝鲜族自治州", "蒙古族藏族自治州", "黎族自治县", "哈萨克自治州",
    "黎族苗族自治县", "土家族苗族自治县", "土家族苗族自治州", "苗族自治县", "侗族自治县", "苗族侗族自治县",
    "苗族侗族自治州", "布依族苗族自治州", "布依族苗族自治州", "苗族土家族自治县", "回族区",
    "市", "区", "县",
  };
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private CityMapper mapper;
  @Autowired
  private CityareaMapper caMapper;

  @Autowired
  private SolidifyMapper smapper;

  public List<City> selectAll() {
    return mapper.selectAll();
  }

  public City getMostSimilarCity(String cityName) {
    List<City> cities = mapper.selectAll();
    for (City city : cities) {
      String strA = cityName;
      String strB = city.getName();
      for (String word : KEY_WORDS) {
        strA = strA.replace(word, "");
        strB = strB.replace(word, "");
      }
      if (strA.compareToIgnoreCase(strB) == 0)
        return city;
      else
        continue;
    }
    List<Cityarea> areas = caMapper.selectAll();
    for (Cityarea area : areas) {
      String strA = cityName;
      String strB = area.getName();
      for (String word : KEY_WORDS) {
        strA = strA.replace(word, "");
        strB = strB.replace(word, "");
      }
      if (strA.compareToIgnoreCase(strB) == 0)
        return mapper.selectByPrimaryKey(area.getCityId());
      else
        continue;
    }
    return null;
  }

  public Cityarea getMostSimilarArea(Integer cityId, String areaName) {

    List<Cityarea> areas = smapper.selectCityAreas(cityId);

    for (Cityarea area : areas) {
      if (areaName.contains("市辖") && area.getName().contains("市辖"))
        return area;

      String strA = areaName;
      String strB = area.getName();
      for (String word : KEY_WORDS) {
        strA = strA.replace(word, "");
        strB = strB.replace(word, "");
      }
      if (strA.compareToIgnoreCase(strB) == 0)
        return area;
      else
        continue;
    }
    return null;
  }
}

