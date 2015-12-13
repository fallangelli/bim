package com.family.grab.downloader;

import com.family.grab.Page;
import com.family.grab.Request;
import com.family.grab.Site;
import com.family.grab.selector.Html;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class of downloader with some common methods.
 *
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public abstract class AbstractDownloader implements Downloader {
  private Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * A simple method to download a url.
   *
   * @param url
   * @return html
   */
  public Html download(String url) {
    return download(url, null);
  }

  /**
   * A simple method to download a url.
   *
   * @param url
   * @return html
   */
  public Html download(String url, String charset) {
    Page page = download(new Request(url), Site.me().setCharset(charset).toTask());
    return (Html) page.getHtml();
  }

  protected void onSuccess(Request request) {
  }

  protected void onError(Request request) {
  }

  protected Page addToCycleRetry(Request request, Site site) {
    Page page = new Page();
    Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
    if (cycleTriedTimesObject == null) {
      logger.info("add CYCLE_TRIED_TIMES for page " + request.getUrl());
      page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
    } else {
      int cycleTriedTimes = (Integer) cycleTriedTimesObject;
      logger.info("retry the " + cycleTriedTimes + " time for page " + request.getUrl());

      cycleTriedTimes++;
      if (cycleTriedTimes >= site.getCycleRetryTimes()) {
        return null;
      }

      page.addTargetRequest(request.setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes));
    }
    page.setNeedCycleRetry(true);
    return page;
  }
}
