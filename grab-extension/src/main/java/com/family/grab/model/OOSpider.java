package com.family.grab.model;

import com.family.grab.Site;
import com.family.grab.Spider;
import com.family.grab.pipeline.CollectorPipeline;
import com.family.grab.pipeline.PageModelPipeline;
import com.family.grab.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * The spider for page com.family.grabserver.model extractor.<br>
 * In grab, we call a POJO containing extract result as "page com.family.grabserver.model". <br>
 * You can customize a crawler by write a page com.family.grabserver.model with annotations. <br>
 * Such as:
 * <pre>
 * {@literal @}TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
 *  public class OschinaBlog{
 *
 *      {@literal @}ExtractBy("//title")
 *      private String title;
 *
 *      {@literal @}ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
 *      private String content;
 *
 *      {@literal @}ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
 *      private List<String> tags;
 * }
 * </pre>
 * And start the spider by:
 * <pre>
 *   OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog")
 *        ,new JsonFilePageModelPipeline(), OschinaBlog.class).run();
 * }
 * </pre>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class OOSpider<T> extends Spider {

  private ModelPageProcessor modelPageProcessor;

  private ModelPipeline modelPipeline;

  private PageModelPipeline pageModelPipeline;

  private List<Class> pageModelClasses = new ArrayList<Class>();

  protected OOSpider(ModelPageProcessor modelPageProcessor) {
    super(modelPageProcessor);
    this.modelPageProcessor = modelPageProcessor;
  }

  public OOSpider(PageProcessor pageProcessor) {
    super(pageProcessor);
  }

  /**
   * create a spider
   *
   * @param site
   * @param pageModelPipeline
   * @param pageModels
   */
  public OOSpider(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
    this(ModelPageProcessor.create(site, pageModels));
    this.modelPipeline = new ModelPipeline();
    super.addPipeline(modelPipeline);
    for (Class pageModel : pageModels) {
      if (pageModelPipeline != null) {
        this.modelPipeline.put(pageModel, pageModelPipeline);
      }
      pageModelClasses.add(pageModel);
    }
  }

  public static OOSpider create(Site site, Class... pageModels) {
    return new OOSpider(site, null, pageModels);
  }

  public static OOSpider create(Site site, PageModelPipeline pageModelPipeline, Class... pageModels) {
    return new OOSpider(site, pageModelPipeline, pageModels);
  }

  @Override
  protected CollectorPipeline getCollectorPipeline() {
    return new PageModelCollectorPipeline<T>(pageModelClasses.get(0));
  }

  public OOSpider addPageModel(PageModelPipeline pageModelPipeline, Class... pageModels) {
    for (Class pageModel : pageModels) {
      modelPageProcessor.addPageModel(pageModel);
      modelPipeline.put(pageModel, pageModelPipeline);
    }
    return this;
  }

}
