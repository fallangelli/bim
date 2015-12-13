package com.family.grab.model;

import com.family.grab.model.annotation.ExtractBy;

/**
 * @author code4crafter@gmail.com
 */
public class BaseRepo {

  @ExtractBy("//ul[@class='pagehead-actions']/li[1]//a[@class='social-count js-social-count']/text()")
  protected int star;
}
