package com.family.webserver.utils.page;


import java.util.List;

public class ObjectListPage<Type> {
  PageInfo pageInfo;
  List<Type> listObject;

  public ObjectListPage(PageInfo pageInfo, List<Type> listObject) {
    this.pageInfo = pageInfo;
    this.listObject = listObject;
  }

  public PageInfo getPageInfo() {
    return pageInfo;
  }

  public void setPageInfo(PageInfo pageInfo) {
    this.pageInfo = pageInfo;
  }

  public List<Type> getListObject() {
    return listObject;
  }

  public void setListObject(List<Type> listObject) {
    this.listObject = listObject;
  }
}
