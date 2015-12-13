package com.family.grab;

import com.family.grab.pipeline.PageModelPipeline;
import junit.framework.Assert;

/**
 * @author code4crafter@gmail.com
 */
public class MockPageModelPipeline implements PageModelPipeline {
  @Override
  public void process(Object o, Task task) {
    Assert.assertNotNull(o);
  }
}
