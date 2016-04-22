package com.family.grabserver.jobs;

import com.family.grabserver.runner.GrabRunner;
import com.family.grabserver.util.EnumType;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GrabTasklet implements Tasklet {
  private static String[] configLocations = new String[]{"applicationContext.xml", "applicationContext-myBatis.xml"};
  private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
  private Integer source;

  private EnumType type;

  private String date;


  /**
   * @param source the message to set
   */
  public void setSource(Integer source) {
    this.source = source;
  }

  /**
   * @param type the message to set
   */
  public void setType(EnumType type) {
    this.type = type;
  }

  /**
   * @param date the message to set
   */
  public void setDate(String date) {
    this.date = date;
  }


  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

    try {
      DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      fmt.parse(date);

      ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
      final GrabRunner runner = applicationContext.getBean(GrabRunner.class);

      switch (type) {
        case all:
          runner.grabCity(source);
          runner.grabCinema(source);
          runner.grabScreening(source);
        case city:
          runner.grabCity(source);
          break;
        case cinema:
          runner.grabCinema(source);
          break;
        case screening:
          runner.grabScreening(source);
          runner.grabMovieContent(source);
          break;
        default:
          throw new Exception("请选择类型，all:所有 city:城市 cinema:影院 screen:放映");
      }
      return RepeatStatus.FINISHED;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
  }

}
