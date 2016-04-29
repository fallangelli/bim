package com.family.grab.pipeline;

import com.family.grab.Task;

/**
 * Implements PageModelPipeline to persistent your page com.family.grabserver.model.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public interface PageModelPipeline<T> {
  public void process(T t, Task task);
}
