package com.family.grabserver.mapper.bim_base;

import com.family.grabserver.entity.bim_base.Comment;

import java.util.List;

public interface CommentMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..comment
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..comment
   *
   * @mbggenerated
   */
  int insert(Comment record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..comment
   *
   * @mbggenerated
   */
  Comment selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..comment
   *
   * @mbggenerated
   */
  List<Comment> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table bim_base..comment
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(Comment record);
}