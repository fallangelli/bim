package com.family.grab.handler;

import com.family.grab.Page;

/**
 * @author code4crafter@gmail.com
 * @date 14-4-5
 */
public interface SubPageProcessor extends RequestMatcher {

  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page
   * @return whether continue to match
   */
  public MatchOther processPage(Page page);

}
