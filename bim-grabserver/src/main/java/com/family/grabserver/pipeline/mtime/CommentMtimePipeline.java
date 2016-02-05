package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.CommentMtime;
import com.family.grabserver.model.mtime.CommentMtimeModel;
import com.family.grabserver.service.mtime.CommentMtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMtimePipeline implements PageModelPipeline<CommentMtimeModel> {

  @Autowired
  private CommentMtimeService service;


  @Override
  public void process(CommentMtimeModel model, Task task) {
    String context = model.getContext();
    JSONObject ob = JSON.parseObject(context);
    JSONArray Comments = (JSONArray) ob.get("comments");
    for (Object CommentOb : Comments) {
      JSONObject Comment = (JSONObject) CommentOb;
      CommentMtime record = new CommentMtime();
      record.setId(Comment.getInteger("id"));
      record.setMovieId(Integer.parseInt(model.getMovieId()));
      record.setTitle(Comment.getString("title"));
      record.setNickName(Comment.getString("nickname"));
      record.setRating(Comment.getFloat("rating"));
      record.setContent(Comment.getString("content"));
      service.insertOrUpdate(record);
    }
  }
}
