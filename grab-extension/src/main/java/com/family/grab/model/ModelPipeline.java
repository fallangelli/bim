package com.family.grab.model;

import com.family.grab.ResultItems;
import com.family.grab.Task;
import com.family.grab.model.annotation.ExtractBy;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grab.pipeline.Pipeline;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The extension to Pipeline for page com.family.grabserver.model extractor.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
class ModelPipeline implements Pipeline {

  private Map<Class, PageModelPipeline> pageModelPipelines = new ConcurrentHashMap<Class, PageModelPipeline>();

  public ModelPipeline() {
  }

  public ModelPipeline put(Class clazz, PageModelPipeline pageModelPipeline) {
    pageModelPipelines.put(clazz, pageModelPipeline);
    return this;
  }

  @Override
  public void process(ResultItems resultItems, Task task) {
    for (Map.Entry<Class, PageModelPipeline> classPageModelPipelineEntry : pageModelPipelines.entrySet()) {
      Object o = resultItems.get(classPageModelPipelineEntry.getKey().getCanonicalName());

      if (o != null) {
        Annotation annotation = classPageModelPipelineEntry.getKey().getAnnotation(ExtractBy.class);
        if (annotation == null || !((ExtractBy) annotation).multi()) {
          classPageModelPipelineEntry.getValue().process(o, task);
        } else {
          List<Object> list = (List<Object>) o;
          for (Object o1 : list) {
            classPageModelPipelineEntry.getValue().process(o1, task);
          }
        }
      }
    }
  }
}
