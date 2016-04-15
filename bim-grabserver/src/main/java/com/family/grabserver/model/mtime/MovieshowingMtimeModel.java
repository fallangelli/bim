package com.family.grabserver.model.mtime;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.mtime.MovieshowingMtimePipeline;
import com.family.grabserver.util.JsonStringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl(value = "http://m.mtime.cn/Service/[\\w\\W]*")
public class MovieshowingMtimeModel implements AfterExtractor {

  @ExtractBy(value = "/html/body/text()")
  private String context;

  @ExtractByUrl("movieId=(\\d*)")
  private String movieId = "";

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final MovieshowingMtimePipeline pipeline = applicationContext.getBean(MovieshowingMtimePipeline.class);

    OOSpider.create(Site.me()
      , pipeline, MovieshowingMtimeModel.class)
      .addUrl("http://m.mtime.cn/Service/callback.mi/movie/Detail.api?movieId=105633").thread(1).run();
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }


  @Override
  public void afterProcess(Page page) {
    context = JsonStringUtil.clearQuotation(context);
    context = context.replace("\\”", "");
  }

}
