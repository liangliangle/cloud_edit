package test.lianglianglee.edit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.lianglianglee"})
@MapperScan("com.lianglianglee.edit.dao")
public class TestApplication {

  /**
   * Start application.
   *
   * @param args 启动传入参数
   */
  public static void main(final String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }

}
