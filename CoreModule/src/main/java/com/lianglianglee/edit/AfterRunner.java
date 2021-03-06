package com.lianglianglee.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Auther :lianglianglee
 * @Description :
 * @Date: 2018年8月27日
 * @Modify:
 */
@Component
public class AfterRunner implements CommandLineRunner {

  @Autowired
  private static Logger LOGGER = LoggerFactory.getLogger(AfterRunner.class);

  /**
   * 会在服务启动完成后立即执行.
   */
  @Override
  public void run(String... args) {
    //刷新其他模块的缓存
    LOGGER.info("系统配置初始化");
  }
}