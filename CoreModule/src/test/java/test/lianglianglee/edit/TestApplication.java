package test.lianglianglee.edit;

import com.doubi.edit.interceptor.ContextInitInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.doubi"})
@MapperScan("com.doubi.edit.dao")
public class TestApplication {

  @Resource
  ContextInitInterceptor contextInitInterceptor;
  /**
   * Start application.
   *
   * @param args 启动传入参数
   */
  public static void main(final String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }

}
