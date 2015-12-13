package com.family.webserver.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 系统工具类，实现Spring ApplicationContextAware接口
 * User: lcheng
 */
@Component
public class SysUtil implements ApplicationContextAware {
  private static String APP_CONFIG_PROP = "appConfig";
  private static ApplicationContext context;
  private static Properties sysConfigProp = null;

  /**
   * 获得系统的某一个Bean
   *
   * @param name
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> T getBean(String name, Class<T> clazz) {
    return SysUtil.context.getBean(name, clazz);
  }

  /**
   * 获得应用系统配置信息
   *
   * @return
   */
  public static Properties getAppConfigProp() {
    if (sysConfigProp == null) {
      sysConfigProp = getBean(APP_CONFIG_PROP, Properties.class);
    }
    return sysConfigProp;
  }

  /**
   * 获得具体的配置值
   *
   * @param propName
   * @return
   */
  public static String getConfigValue(String propName) {
    return SysUtil.getAppConfigProp().getProperty(propName);
  }

  /**
   * 获得具体的配置值，当配置项不存在的时候返回指定的默认值
   *
   * @param propName
   * @param defaultValue
   * @return
   */
  public static String getConfigValue(String propName, String defaultValue) {
    return SysUtil.getAppConfigProp().getProperty(propName, defaultValue);
  }

  /**
   * 获得定义的消息
   *
   * @param key
   * @param params
   * @return
   */
  public static String getMessage(String key, String... params) {
    return SysUtil.context.getMessage(key, params, null);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SysUtil.context = applicationContext;
  }
}
