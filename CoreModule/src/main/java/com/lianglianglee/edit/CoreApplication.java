package com.lianglianglee.edit;

import org.lianglianglee.oss.service.OssService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

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


  @Bean
  public OssService ossService() {
    return new OssService();
  }
}
