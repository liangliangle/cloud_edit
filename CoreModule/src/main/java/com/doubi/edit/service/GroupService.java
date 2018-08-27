package com.doubi.edit.service;

import com.doubi.edit.dto.result.base.GroupDto;

import java.util.List;

public interface GroupService {

    void insert(GroupDto dto);

    void update(GroupDto dto);

    void delete(Long id);

    GroupDto getById(Long id);

    List<GroupDto> getByUser(Long userId);


}
