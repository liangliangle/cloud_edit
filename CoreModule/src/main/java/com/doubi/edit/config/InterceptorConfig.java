package com.doubi.edit.config;

import com.doubi.edit.common.interceptor.ContextInitInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置拦截器
 * <p>
 * <p>
 * WebMvcConfigurer在springboot 2.0中过时
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

  private Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    logger.info("拦截器注册");
    registry.addInterceptor(new ContextInitInterceptor()).addPathPatterns("/api/**");
    super.addInterceptors(registry);
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
