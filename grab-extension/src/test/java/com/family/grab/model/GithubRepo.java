package com.family.grab.model;

import com.family.grab.Site;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.HelpUrl;
import com.family.grab.model.annotation.TargetUrl;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl({"https://github.com/\\w+\\?tab=repositories", "https://github.com/\\w+", "https://github.com/explore/*"})
public class GithubRepo extends BaseRepo {

  @ExtractBy("//ul[@class='pagehead-actions']/li[2]//a[@class='social-count']/text()")
  private int fork;

  public static void main(String[] args) {
    OOSpider.create(Site.me().setSleepTime(100)
      , new ConsolePageModelPipeline(), GithubRepo.class)
      .addUrl("https://github.com/code4craft").thread(10).run();
  }

  public int getStar() {
    return star;
  }

  public int getFork() {
    return fork;
  }
}
