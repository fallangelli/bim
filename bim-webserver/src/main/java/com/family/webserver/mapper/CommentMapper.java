package com.family.webserver.mapper;

import com.family.webserver.entity.Comment;

import java.util.List;

public interface CommentMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table comment
   *
   * @mbggenerated Tue Feb 02 16:34:46 CST 2016
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table comment
   *
   * @mbggenerated Tue Feb 02 16:34:46 CST 2016
   */
  int insert(Comment record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table comment
   *
   * @mbggenerated Tue Feb 02 16:34:46 CST 2016
   */
  Comment selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table comment
   *
   * @mbggenerated Tue Feb 02 16:34:46 CST 2016
   */
  List<Comment> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table comment
   *
   * @mbggenerated Tue Feb 02 16:34:46 CST 2016
   */
  int updateByPrimaryKey(Comment record);
}
