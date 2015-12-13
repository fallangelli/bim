package com.family.grab.downloader;

import com.family.grab.Spider;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author code4crafter@gmail.com <br>
 */
public class FileCacheTest {

  @Ignore("takes long")
  @Test
  public void test() {
    FileCache fileCache = new FileCache("http://my.oschina.net/flashsword/blog", "http://my.oschina.net/flashsword/blog/*");
    Spider.create(fileCache).downloader(fileCache).pipeline(fileCache).run();
  }
}
