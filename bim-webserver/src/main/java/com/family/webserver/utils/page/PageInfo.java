package com.family.webserver.utils.page;

import com.family.webserver.utils.SysUtil;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {

  private static final long serialVersionUID = 587754556498974978L;

  //总页数
  private int totalPage;
  //总记录数
  private int totalResult;
  //当前页
  private int currentPage;
  //当前显示到的ID, 在mysql limit 中就是第一个参数.
  private int currentResult;
  //每页的大小
  private int pageSize;

  private List<T> data;

  public PageInfo() {
  }

  public PageInfo(int totalCount, int pageSize, int currentPage) {
    this.totalResult = totalCount;
    this.pageSize = (pageSize <= 0) ? Integer.valueOf(SysUtil.getConfigValue("showCount", "10")) : pageSize;
    this.currentPage = currentPage;
  }

  public int getTotalPage() {
    if (this.pageSize <= 0) {
      this.pageSize = Integer.valueOf(SysUtil.getConfigValue("showCount", "10"));
    }
    this.totalPage = (totalResult % pageSize == 0) ? (totalResult / pageSize) : (1 + totalResult / pageSize);
    return this.totalPage;
  }

  //应该将该方法废掉
  @Deprecated
  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getTotalResult() {
    return totalResult;
  }

  public void setTotalResult(int totalResult) {
    this.totalResult = totalResult;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getCurrentResult() {
    return this.currentResult = (this.currentPage - 1) * this.pageSize;
  }

  public void setCurrentResult(int currentResult) {
    this.currentResult = currentResult;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
