package com.doubi.edit.common.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 运行时项目配置属性.
 *
 * @author 李亮亮
 */
public class ConfigurationUtil {

  public static final String FONT_STR = "font.str";

  static Properties prop = new Properties();

  static {
    try {
      prop.load(ConfigurationUtil.class.getClassLoader().getResourceAsStream("common.properties"));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * 返回配置项.
   */
  public static String getProperty(String key) {
    return prop.getProperty(key);
  }

}
