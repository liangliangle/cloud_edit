package com.lianglianglee.edit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements EnvironmentAware {
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    @SuppressWarnings("unused")
    private RelaxedPropertyResolver propertyResolver;
    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "swagger.");
    }
    /**
     * Swagger definition.
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity.class).select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN)) // and by paths
                .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return swaggerSpringMvcPlugin;
    }
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("点点云笔记系统 api 文档", // 大标题
                "用心做好每一个接口", // 小标题
                "v1.0", // 版本
                "", "lianglianglee", // 作者
                "Talk is cheep, show me the code", // 链接显示文字
                ""// 网站链接
        );
        return apiInfo;
    }

}
