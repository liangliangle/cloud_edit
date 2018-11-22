package com.lianglianglee.edit.controller;

import com.lianglianglee.edit.dto.DemoDto;
import com.lianglianglee.edit.dto.MonitorInfoBean;
import com.lianglianglee.edit.service.DemoService;
import com.lianglianglee.edit.service.MonitorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
@Api(value = "无效接口", consumes = "application/json")
public class DemoController  {
  @Autowired
  private DemoService demoService;
  @Autowired
  private MonitorService monitorService;


  @GetMapping("/local")
  public List<DemoDto> local() {
    return this.demoService.getAll();
  }

  @GetMapping("/info")
  private MonitorInfoBean getIMonitor() throws Exception {
    return monitorService.getMonitorInfoBean();
  }


}
