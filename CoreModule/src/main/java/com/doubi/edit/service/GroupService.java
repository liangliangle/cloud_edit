package com.doubi.edit.service;

import com.doubi.edit.dto.create.GroupCreateDto;
import com.doubi.edit.dto.result.GroupDetailDto;
import com.doubi.edit.dto.result.base.GroupDto;
import com.doubi.edit.dto.update.GroupUpdateDto;

import java.util.List;

public interface GroupService {

  void insert(GroupCreateDto dto);

  void update(GroupUpdateDto dto, Long id);

  void delete(Long id);

  GroupDetailDto getById(Long id);

  List<GroupDto> getByUser(Long userId);


}
