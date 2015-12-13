package com.family.grab.pipeline;

import com.family.grab.Request;
import com.family.grab.ResultItems;
import com.family.grab.Site;
import com.family.grab.Task;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by ywooer on 2014/5/6 0006.
 */
public class FilePipelineTest {

  private static ResultItems resultItems;
  private static Task task;

  @BeforeClass
  public static void before() {
    resultItems = new ResultItems();
    resultItems.put("content", "grab 爬虫工具");
    Request request = new Request("http://www.baidu.com");
    resultItems.setRequest(request);

    task = new Task() {
      @Override
      public String getUUID() {
        return UUID.randomUUID().toString();
      }

      @Override
      public Site getSite() {
        return null;
      }
    };
  }

  @Test
  public void testProcess() {
    FilePipeline filePipeline = new FilePipeline();
    filePipeline.process(resultItems, task);
  }
}
