package com.doubi.edit.controller;

import com.doubi.edit.common.controller.BaseController;
import com.doubi.edit.dto.DemoDto;
import com.doubi.edit.service.DemoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
@Api(value = "无效接口", consumes = "application/json")
public class DemoController extends BaseController {
  @Autowired
  private DemoService demoService;


  @GetMapping("/local")
  public List<DemoDto> local() {
    return this.demoService.getAll();
  }

}
