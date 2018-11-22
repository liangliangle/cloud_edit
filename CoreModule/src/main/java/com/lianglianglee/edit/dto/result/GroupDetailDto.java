package com.lianglianglee.edit.dto.result;

import com.lianglianglee.edit.dto.result.base.GroupDto;
import com.lianglianglee.edit.dto.result.base.GroupUserDto;

import java.util.List;

public class GroupDetailDto {

  private GroupDto dto;

  private List<GroupUserDto> users;

  public GroupDto getDto() {
    return dto;
  }

  public void setDto(GroupDto dto) {
    this.dto = dto;
  }

  public List<GroupUserDto> getUsers() {
    return users;
  }

  public void setUsers(List<GroupUserDto> users) {
    this.users = users;
  }
}
