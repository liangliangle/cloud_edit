package com.doubi.edit.controller;

import com.doubi.edit.common.controller.BaseController;
import com.doubi.edit.dto.create.GroupCreateDto;
import com.doubi.edit.dto.result.base.GroupDto;
import com.doubi.edit.dto.result.GroupDetailDto;
import com.doubi.edit.dto.update.GroupUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/group")
@Api(value = "小组相关接口", consumes = "application/json")
public class GroupController extends BaseController {

    @PostMapping("")
    @ApiOperation("创建小组")
    public void create(@RequestBody GroupCreateDto dto) {

    }

    @GetMapping("{id}")
    @ApiOperation("获取小组详情")
    public GroupDetailDto getById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("获取用户所在小组")
    public List<GroupDto> getByUser(@PathVariable("userId") Long userId){
        return null;
    }

    @PutMapping("rename")
    @ApiOperation("修改小组信息")
    public void update(@RequestBody GroupUpdateDto dto) {

    }

    @PutMapping("convey")
    @ApiOperation("转让小组")
    public void conveyGroup(@RequestBody GroupCreateDto dto) {

    }

}
