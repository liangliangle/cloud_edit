package com.doubi.edit.controller;

import com.doubi.edit.common.controller.BaseController;
import com.doubi.edit.dto.create.GroupCreateDto;
import com.doubi.edit.dto.result.GroupDetailDto;
import com.doubi.edit.dto.result.base.GroupDto;
import com.doubi.edit.dto.update.GroupUpdateDto;
import com.doubi.edit.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@Api(value = "小组相关接口", consumes = "application/json")
public class GroupController extends BaseController {
  @Autowired
  private GroupService groupService;


  private static Logger logger = LoggerFactory.getLogger(GroupController.class);

  @PostMapping("")
  @ApiOperation("创建小组")
  public void create(@RequestBody GroupCreateDto dto) {
    groupService.insert(dto);
  }

  @GetMapping("{id}")
  @ApiOperation("获取小组详情")
  public GroupDetailDto getById(@PathVariable("id") Long id) {
    logger.info(request.getRequestURI());
    return groupService.getById(id);
  }

  @GetMapping("/user/{userId}")
  @ApiOperation("获取用户所在小组")
  public List<GroupDto> getByUser(@PathVariable("userId") Long userId) {
    logger.info(request.getRequestURI());
    return groupService.getByUser(userId);
  }

  @PutMapping("rename/{id}")
  @ApiOperation("修改小组信息")
  public void update(@RequestBody GroupUpdateDto dto, @PathVariable("id") Long id) {
    logger.info(request.getRequestURI());
    groupService.update(dto, id);

  }

  @PutMapping("convey{id}")
  @ApiOperation("转让小组")
  public void conveyGroup() {
    logger.info(request.getRequestURI());
//todo
  }

}
