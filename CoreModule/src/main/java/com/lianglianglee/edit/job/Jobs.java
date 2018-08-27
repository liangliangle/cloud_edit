package com.lianglianglee.edit.job;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class Jobs {

  private static Logger logger = Logger.getLogger(Jobs.class);

  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


  @Scheduled(fixedDelay = 60000)
  public void checkNotice() {

  }


  @Scheduled(cron = "0 1 0 * * ?")
  public void activityJob() {

  }
}
