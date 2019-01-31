package com.lianglianglee.edit;

import org.lianglianglee.oss.service.OssService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@RestController
@EnableScheduling
@ComponentScan(basePackages = {"com.lianglianglee"})
@MapperScan("com.lianglianglee.edit.dao")
public class CoreApplication {


  /**
   * Start application.
   *
   * @param args 启动传入参数
   */
  public static void main(final String[] args) {
    SpringApplication.run(CoreApplication.class, args);
  }

/*
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setLocation("/data/tmp");
    return factory.createMultipartConfig();
  }*/
  @Bean
  public OssService ossService() {
    return new OssService();
  }
}
