package com.family.grab.model;

import com.family.grab.Site;
import com.family.grab.Task;
import com.family.grab.downloader.MockGithubDownloader;
import com.family.grab.pipeline.PageModelPipeline;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com <br>
 */
public class GithubRepoTest {

  @Test
  public void test() {

    OOSpider.create(Site.me().setSleepTime(0)
      , new PageModelPipeline<GithubRepo>() {
        @Override
        public void process(GithubRepo o, Task task) {
          assertThat(o.getStar()).isEqualTo(86);
          assertThat(o.getFork()).isEqualTo(70);
        }
      }, GithubRepo.class).addUrl("https://github.com/code4craft/grab").setDownloader(new MockGithubDownloader()).test("https://github.com/code4craft/grab");

  }
}
