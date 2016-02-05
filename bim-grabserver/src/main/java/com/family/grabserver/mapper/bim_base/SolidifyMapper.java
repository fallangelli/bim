package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.Cityarea;

import java.util.List;

public interface SolidifyMapper {

  List<Cityarea> selectCityareas(Integer cityId);

  void fillPositions();

  void merge_comment_mtime(Integer sourceCode);

  void merge_image_mtime(Integer sourceCode);


  void merge_screening_mtime(Integer sourceCode);

  void merge_screening_maoyan(Integer sourceCode);

  void merge_screening_baidu(Integer sourceCode);

  void merge_screening_weixin(Integer sourceCode);
}
