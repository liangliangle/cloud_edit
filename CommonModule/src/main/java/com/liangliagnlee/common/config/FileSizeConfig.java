package com.liangliagnlee.common.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileSizeConfig {

  /**
   * 文件上传配置.
   */
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigElement element = new MultipartConfigElement(
      "", 10240000, 10240000, 0);
    return element;
  }

}
