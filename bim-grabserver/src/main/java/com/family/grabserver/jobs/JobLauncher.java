package com.family.grabserver.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/15.
 */
public class JobLauncher {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("job-config.xml");
    org.springframework.batch.core.launch.JobLauncher launcher = (org.springframework.batch.core.launch.JobLauncher) context.getBean("jobLauncher");
    Job job = (Job) context.getBean("syncJob");

    try {
            /* 运行Job */
      JobExecution result = launcher.run(job, new JobParametersBuilder()
        .addString("type", "screening")
        .addLong("source", (long) 0)
        .addDate("date", new Date())
        .toJobParameters()
      );
            /* 处理结束，控制台打印处理结果 */
      System.out.println(result.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
