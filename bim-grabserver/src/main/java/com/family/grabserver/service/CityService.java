package com.family.grabserver.service;

import com.family.grabserver.entity.bim_base.City;
import com.family.grabserver.entity.bim_base.Cityarea;
import com.family.grabserver.mapper.bim_base.CityMapper;
import com.family.grabserver.mapper.bim_base.CityareaMapper;
import com.family.grabserver.mapper.bim_base.SolidifyMapper;
import com.family.grabserver.util.CityMerge;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CityService {

  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private CityMapper mapper;
  @Autowired
  private CityareaMapper caMapper;

  @Autowired
  private SolidifyMapper smapper;

  private List<City> allCities;

  private List<Cityarea> allCityAreas;

  public List<City> getAllCities() {
    if (allCities == null || allCities.size() <= 0)
      allCities = mapper.selectAll();
    return allCities;
  }

  public List<Cityarea> getAllCityAreas() {
    if (allCityAreas == null || allCityAreas.size() <= 0)
      allCityAreas = caMapper.selectAll();
    return allCityAreas;
  }

  public List<City> selectAll() {
    return allCities;
  }

  public City getMostSimilarCity(String cityName) {
    List<City> cities = getAllCities();
    for (City city : cities) {
      if (CityMerge.compareWithouKeyWord(cityName, city.getName()))
        return city;
      else
        continue;
    }
    List<Cityarea> areas = getAllCityAreas();
    for (Cityarea area : areas) {
      if (CityMerge.compareWithouKeyWord(cityName, area.getName()))
        return mapper.selectByPrimaryKey(area.getCityId());
      else
        continue;
    }
    return null;
  }

  public Cityarea getMostSimilarArea(Integer cityId, String cityName, String areaName) {
    City city = mapper.selectByPrimaryKey(cityId);
    if (CityMerge.compareWithouKeyWord(city.getName(), "东莞") ||
      CityMerge.compareWithouKeyWord(city.getName(), "中山")) {
      List<Cityarea> areas = smapper.selectCityareas(cityId);
      return areas.get(0);
    }

    if (CityMerge.compareWithouKeyWord(city.getName(), areaName)) {
      List<Cityarea> areas = smapper.selectCityareas(cityId);
      for (Cityarea area : areas) {
        if (area.getName().contains("市辖") ||
          CityMerge.compareWithouKeyWord(city.getName(), area.getName()) ||
          CityMerge.compareWithouKeyWord(cityName, area.getName()))
          return area;
      }
      return null;
    }

    if (CityMerge.compareWithouKeyWord(city.getName(), areaName)) {
      List<Cityarea> areas = smapper.selectCityareas(cityId);
      for (Cityarea area : areas) {
        if (area.getName().contains("市辖") ||
          CityMerge.compareWithouKeyWord(city.getName(), area.getName()))
          return area;
      }
      return null;
    }

    List<Cityarea> areas = smapper.selectCityareas(cityId);
    for (Cityarea area : areas) {
      if (areaName.contains("市辖") && area.getName().contains("市辖"))
        return area;
      if (CityMerge.compareWithouKeyWord(cityName, area.getName()))
        return area;

      if (CityMerge.compareWithouKeyWord(areaName, area.getName()))
        return area;
      else
        continue;
    }
    return null;
  }
}

