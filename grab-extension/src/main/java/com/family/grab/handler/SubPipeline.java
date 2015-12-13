package com.family.grab.handler;

import com.family.grab.ResultItems;
import com.family.grab.Task;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public interface SubPipeline extends RequestMatcher {

  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page
   * @param task
   * @return whether continue to match
   */
  public MatchOther processResult(ResultItems resultItems, Task task);

}
