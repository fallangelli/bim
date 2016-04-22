package com.family.grabserver.jobs;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * Created by Administrator on 2016/4/18.
 */
public class MessagesDecider implements JobExecutionDecider {

  public FlowExecutionStatus decide(JobExecution jobExecution,
                                    StepExecution stepExecution) {
    String exitCode = stepExecution.getExitStatus().getExitCode();
    if (!exitCode.equals(ExitStatus.FAILED.getExitCode())) {
      return new FlowExecutionStatus("COMPLETED");
    } else {
      return FlowExecutionStatus.FAILED;
    }
  }

}
