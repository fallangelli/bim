package com.family.grabserver.pipeline;

import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.model.DoubanComing;
import org.springframework.stereotype.Component;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("DoubanComingPipeline")
public class DoubanComingPipeline implements PageModelPipeline<DoubanComing> {


  @Override
  public void process(DoubanComing doubanComing, Task task) {
  }
}
