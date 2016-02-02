package com.family.grabserver.model.mtime;

import com.family.grab.Page;
import com.family.grab.Site;
import com.family.grab.model.AfterExtractor;
import com.family.grab.model.OOSpider;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.model.annotation.ExtractByUrl;
import com.family.grab.model.annotation.TargetUrl;
import com.family.grabserver.pipeline.mtime.CommentMtimePipeline;
import com.family.grabserver.util.JsonStringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TargetUrl(value = "http://m.mtime.cn/Service/[\\w\\W]*")
public class CommentMtimeModel implements AfterExtractor {

  @ExtractBy(value = "/html/body")
  private String context;

  @ExtractByUrl("movieId=(\\d*)")
  private String movieId = "";

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    final CommentMtimePipeline pipeline = applicationContext.getBean(CommentMtimePipeline.class);

    OOSpider.create(Site.me()
      , pipeline, CommentMtimeModel.class)
      .addUrl("http://m.mtime.cn/Service/callback.mi/Movie/HotLongComments.api?movieId=55024&pageIndex=1").thread(1).run();
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
    context = context.replace("<body>", "");
    context = context.replace("</body>", "");
    context = context.replace("</moon>", "");
    context = context.replace("<moon river=\"\">", "");

    context = JsonStringUtil.clearQuotation(context);
    context = context.replace("\\”", "");
  }

}
