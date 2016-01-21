package com.family.grabserver.mapper;

import com.family.grabserver.entity.Cityarea;

import java.util.List;

public interface SolidifyMapper {

  List<Cityarea> selectCityAreas(Integer cityId);

  void merge_city_maoyan();

  void merge_city_mtime();

  void merge_city_baidu();

  void merge_city_area_maoyan();

  void merge_city_area_mtime();

  void merge_city_area_baidu();

  void merge_movieshowing_maoyan();

  void merge_movieshowing_mtime();

  void merge_movieshowing_baidu();

  void merge_cinema_mtime();

  void merge_cinema_maoyan();

  void merge_cinema_baidu();

  void merge_screening_mtime();

  void merge_screening_maoyan();

  void merge_screening_baidu();
}
