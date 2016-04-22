package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.Cityarea;
import org.apache.ibatis.annotations.Param;

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

  void clear_u(@Param("type") String type, @Param("source") Integer source);

  void clear_d(@Param("type") String type);

  void clear_screening(@Param("source") Integer source);



}
