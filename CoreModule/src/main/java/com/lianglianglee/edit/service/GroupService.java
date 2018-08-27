package com.lianglianglee.edit.service;

import com.lianglianglee.edit.dto.result.GroupDto;

import java.util.List;

public interface GroupService {

   void insert(GroupDto dto);
  void update(GroupDto dto);

  void delete(Long id);

  GroupDto getById(Long id);

  List<GroupDto> getByUser(Long userId);



}
