package com.doubi.edit.config;

import com.doubi.edit.interceptor.ContextInitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * 配置拦截器
 * <p>
 * <p>
 * WebMvcConfigurer在springboot 2.0中过时
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  @Resource
  ContextInitInterceptor contextInitInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(contextInitInterceptor);
  }
}
