package com.family.grab;

/**
 * Interface for identifying different tasks.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @see com.family.grab.scheduler.Scheduler
 * @see com.family.grab.pipeline.Pipeline
 * @since 0.1.0
 */
public interface Task {

  /**
   * unique id for a task.
   *
   * @return uuid
   */
  public String getUUID();

  /**
   * site of a task
   *
   * @return site
   */
  public Site getSite();

}
