package com.lianglianglee.edit.controller;

import java.util.List;

import com.liangliagnlee.common.interceptor.HttpContext;
import com.liangliagnlee.common.utils.Check;
import com.lianglianglee.edit.dto.create.GroupCreateDto;
import com.lianglianglee.edit.dto.create.GroupUserCreateDto;
import com.lianglianglee.edit.dto.result.base.GroupDto;
import com.lianglianglee.edit.dto.result.base.GroupUserDto;
import com.lianglianglee.edit.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/group")
@Api(value = "小组相关接口", consumes = "application/json")
public class GroupController {
  @Autowired
  private GroupService groupService;

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
