package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.Cityarea;

import java.util.List;

public interface SolidifyMapper {

  List<Cityarea> selectCityareas(Integer cityId);

  void fillPositions();

  void merge_screening_mtime();

  void merge_screening_maoyan();

  void merge_screening_baidu();
}
