package com.family.grabserver.jobs;

import com.family.grabserver.runner.GrabRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class QuartzJob {
  private static String[] configLocations = new String[]{"applicationContext.xml", "applicationContext-myBatis.xml"};

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("抓取任务开始执行!");
    ApplicationContext ctx = new ClassPathXmlApplicationContext("job-config.xml");
  }

  public void work() {
    System.out.println("work任务调度！！！" + (new Date()).toString());
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
    final GrabRunner runner = applicationContext.getBean(GrabRunner.class);
//    runner.grabCity();
//    runner.grabCinema();
//    runner.grabScreening();
//    runner.mergeService.merge();
  }

}
