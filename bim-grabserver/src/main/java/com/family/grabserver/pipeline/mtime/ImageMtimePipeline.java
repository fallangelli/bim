package com.family.grabserver.pipeline.mtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.grab.Task;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grabserver.entity.bim_grab.ImageMtime;
import com.family.grabserver.model.mtime.ImageMtimeModel;
import com.family.grabserver.service.mtime.ImageMtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageMtimePipeline implements PageModelPipeline<ImageMtimeModel> {

  @Autowired
  private ImageMtimeService service;


  @Override
  public void process(ImageMtimeModel model, Task task) {
    String context = model.getContext();
    JSONArray Images = JSON.parseArray(context);
    for (Object ImageOb : Images) {
      JSONObject Image = (JSONObject) ImageOb;
      ImageMtime record = new ImageMtime();
      record.setId(Image.getInteger("id"));
      record.setMovieId(Integer.parseInt(model.getMovieId()));
      record.setImageUrl(Image.getString("image"));

      service.insertOrUpdate(record);
    }
  }
}
