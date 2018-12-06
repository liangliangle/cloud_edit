package com.lianglianglee.edit.controller;

import com.liangliagnlee.common.interceptor.HttpContext;
import com.liangliagnlee.common.utils.Check;
import com.lianglianglee.edit.dto.create.GroupCreateDto;
import com.lianglianglee.edit.dto.create.GroupUserCreateDto;
import com.lianglianglee.edit.dto.result.base.GroupDto;
import com.lianglianglee.edit.dto.result.base.GroupUserDto;
import com.lianglianglee.edit.service.GroupService;
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
public class GroupController  {
  @Autowired
  private GroupService groupService;


  private static Logger logger = LoggerFactory.getLogger(GroupController.class);

  @PostMapping("")
  @ApiOperation("创建小组")
  public void create(@RequestBody GroupCreateDto dto) {
    dto.setUserName(HttpContext.getContext().getUserName());
    dto.setUserId(HttpContext.getContext().getUserId());
    Check.check(dto);
    groupService.insert(dto);
  }

  @GetMapping("{id}")
  @ApiOperation("获取小组详情")
  public GroupDto getById(@PathVariable("id") Long id) {
    return groupService.getById(id);
  }

  @GetMapping("/user/{userId}")
  @ApiOperation("获取用户所在小组")
  public List<GroupDto> getByUser(@PathVariable("userId") Long userId) {
    return groupService.getByUser(userId);
  }


  @PutMapping("/{id}")
  @ApiOperation("修改小组信息")
  public void update(@RequestBody GroupCreateDto dto, @PathVariable("id") Long id) {
    groupService.update(dto, id);
  }


  @GetMapping("{id}/user")
  @ApiOperation("获取小组用户")
  public List<GroupUserDto> update(@PathVariable("id") Long id) {
    return groupService.getUsers(id);
  }

  @PutMapping("convey/{id}")
  @ApiOperation("转让小组")
  public void conveyGroup(@PathVariable("id") Long id, @RequestParam("userId") Long userId) {
    groupService.conveyGroup(id, userId);
  }


  @DeleteMapping("/{id}/user/{userId}")
  @ApiOperation("转让小组")
  public void removeUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
    groupService.removeUser(id, userId);
  }

  @PostMapping("user")
  @ApiOperation("邀请用户到小组")
  public void addUser(@RequestBody GroupUserCreateDto dto) {
    groupService.addUserByGroup(dto);
  }

}
