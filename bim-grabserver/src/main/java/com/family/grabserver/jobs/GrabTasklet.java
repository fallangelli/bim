package com.family.grabserver.jobs;

import com.family.grabserver.runner.GrabRunner;
import com.family.grabserver.util.EnumType;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class GrabTasklet implements Tasklet {
  private static String[] configLocations = new String[]{"applicationContext.xml", "applicationContext-myBatis.xml"};
  private Integer source;

  private EnumType type;

  private Date date;


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
  public void setDate(Date date) {
    this.date = date;
  }


  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    try {
//      EnumType enumType = (EnumType) Enum.Parse(typeof(EnumType), "Ticket");


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
          break;
        default:
          throw new Exception("请选择类型，all:所有 city:城市 cinema:影院 screen:放映");
      }
      return RepeatStatus.FINISHED;
    } catch (Exception e) {
      return RepeatStatus.CONTINUABLE;
    }
  }

}
