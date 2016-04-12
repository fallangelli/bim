package com.family.grabserver.runner;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("MyTasklet")
public class MyTasklet implements Tasklet {
  private DataSource dataSource;
  private String sql = "select firstName,lastName,school from PERSON;";

  public RepeatStatus execute(StepContribution step, ChunkContext chunk)
    throws Exception {
//		List<Person> person = new ArrayList<Person>();
//		JdbcTemplate myTemplate = new JdbcTemplate(getDataSource());
//		person = myTemplate.query(sql, new PersonMapper());
//
//		for(Person p: person){
//			System.out.println(p);
//		}
    for (int i = 0; i < 10; i++)
      System.out.println(i);

    return RepeatStatus.FINISHED;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

}
