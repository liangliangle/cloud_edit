package com.doubi.edit.dto.result;

import com.doubi.edit.dto.result.base.GroupDto;
import com.doubi.edit.dto.result.base.UserDto;

import java.util.List;

public class GroupDetailDto {

  private GroupDto dto;

  private List<UserDto> users;

  public GroupDto getDto() {
    return dto;
  }

  public void setDto(GroupDto dto) {
    this.dto = dto;
  }

  public List<UserDto> getUsers() {
    return users;
  }

  public void setUsers(List<UserDto> users) {
    this.users = users;
  }
}
