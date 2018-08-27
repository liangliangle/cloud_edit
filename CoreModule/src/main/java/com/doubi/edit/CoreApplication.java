package com.doubi.edit;

import org.lianglianglee.oss.service.OssService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
@ComponentScan(basePackages = {"com.doubi"})
@MapperScan("com.doubi.edit.dao")
public class CoreApplication extends SpringBootServletInitializer implements ApplicationContextAware {
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(CoreApplication.class);
    }

    /**
     * Start application.
     *
     * @param args 启动传入参数
     */
    public static void main(final String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

    /**
     * 解决 Feignclient与springfox Swagger的集成冲突问题 在不升级springfox的前提下，使用work,around.
     * https://github.com/springfox/springfox/issues/1074
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // force the bean to get loaded as soon as possible
        applicationContext.getBean("requestMappingHandlerAdapter");
    }

    @Bean
    public OssService ossService() {
        return new OssService();
    }
}
