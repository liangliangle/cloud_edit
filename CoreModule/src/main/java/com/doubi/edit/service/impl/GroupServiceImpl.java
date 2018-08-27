package com.doubi.edit.service.impl;

import com.doubi.edit.common.utils.BeanUtils;
import com.doubi.edit.dao.GroupDAO;
import com.doubi.edit.dao.GroupUserDAO;
import com.doubi.edit.dto.result.base.GroupDto;
import com.doubi.edit.service.GroupService;
import com.doubi.edit.entity.GroupEntity;
import com.doubi.edit.entity.GroupUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroupServiceImpl implements GroupService {

  @Autowired
  private GroupDAO groupDAO;
  @Autowired
  private GroupUserDAO groupUserDAO;

  @Override
  public void insert(GroupDto dto) {
    GroupEntity groupEntity = BeanUtils.DtoToEntity(dto, GroupEntity.class);
    groupEntity.buildDefaultTimeStamp();
    groupDAO.insert(groupEntity);
    GroupUserEntity entity = new GroupUserEntity();
    entity.setGroupId(groupEntity.getId());
    entity.setUserId(dto.getUserId());
    entity.setType("管理");
    entity.setStatus(1);
    entity.buildDefaultTimeStamp();
    groupUserDAO.insert(entity);
  }

  @Override
  public void update(GroupDto dto) {
    GroupEntity entity = groupDAO.selectById(dto.getId());
    entity.buildDefaultLastTime();
    entity.setName(dto.getName());
    groupDAO.insert(entity);

  }

  @Override
  public void delete(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    if(entity.getType().equals(""))
    groupDAO.insert(entity);
  }

  @Override
  public GroupDto getById(Long id) {
    return null;
  }

  @Override
  public List<GroupDto> getByUser(Long userId) {
    return null;
  }
}
