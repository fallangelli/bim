package com.family.grabserver.runner;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScheduledJob extends QuartzJobBean {

  @Override
  protected void executeInternal(JobExecutionContext arg0)
    throws JobExecutionException {

    MainRunner runner = new MainRunner();
    runner.execute();
  }
}
