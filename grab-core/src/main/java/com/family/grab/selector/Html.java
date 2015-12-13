package com.family.grab.selector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Selectable html.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class Html extends HtmlNode {

  /**
   * Disable jsoup html entity escape. It can be set just before any Html instance is created.
   */
  public static boolean DISABLE_HTML_ENTITY_ESCAPE = true;
  private static volatile boolean INITED = false;
  private Logger logger = LoggerFactory.getLogger(getClass());
  /**
   * Store parsed document for better performance when only one text exist.
   */
  private Document document;

  public Html(String text) {
    try {
      disableJsoupHtmlEntityEscape();
      this.document = Jsoup.parse(text);
    } catch (Exception e) {
      this.document = null;
      logger.warn("parse document error ", e);
    }
  }

  public Html(Document document) {
    this.document = document;
  }

  public static Html create(String text) {
    return new Html(text);
  }

  /**
   * Disable jsoup html entity escape. It is a hack way only for jsoup 1.7.2.
   */
  private void disableJsoupHtmlEntityEscape() {
    if (DISABLE_HTML_ENTITY_ESCAPE && !INITED) {
      Entities.EscapeMode.base.getMap().clear();
      Entities.EscapeMode.extended.getMap().clear();
      INITED = true;
    }
  }

  public Document getDocument() {
    return document;
  }

  @Override
  protected List<Element> getElements() {
    return Collections.<Element>singletonList(getDocument());
  }

  /**
   * @param selector
   * @return
   */
  public String selectDocument(Selector selector) {
    if (selector instanceof ElementSelector) {
      ElementSelector elementSelector = (ElementSelector) selector;
      return elementSelector.select(getDocument());
    } else {
      return selector.select(getFirstSourceText());
    }
  }

  public List<String> selectDocumentForList(Selector selector) {
    if (selector instanceof ElementSelector) {
      ElementSelector elementSelector = (ElementSelector) selector;
      return elementSelector.selectList(getDocument());
    } else {
      return selector.selectList(getFirstSourceText());
    }
  }

}
