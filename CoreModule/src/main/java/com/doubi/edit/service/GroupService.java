package com.doubi.edit.service;

import com.doubi.edit.dto.create.GroupCreateDto;
import com.doubi.edit.dto.create.GroupUserCreateDto;
import com.doubi.edit.dto.result.GroupDetailDto;
import com.doubi.edit.dto.result.base.GroupDto;

import java.util.List;

public interface GroupService {

  void insert(GroupCreateDto dto);

  void update(String newName, Long id);

  void delete(Long id);

  GroupDetailDto getById(Long id);

  List<GroupDto> getByUser(Long userId);

  void conveyGroup(Long id, Long userId);

  void addUserByGroup(GroupUserCreateDto dto);


  //void submitToGroup(Long id, Boolean status);


}
