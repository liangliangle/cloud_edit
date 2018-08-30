package com.doubi.edit.service.impl;

import com.doubi.edit.common.exception.ServiceException;
import com.doubi.edit.common.utils.BeanUtils;
import com.doubi.edit.dao.GroupDAO;
import com.doubi.edit.dao.GroupUserDAO;
import com.doubi.edit.dto.create.GroupCreateDto;
import com.doubi.edit.dto.result.GroupDetailDto;
import com.doubi.edit.dto.result.base.GroupDto;
import com.doubi.edit.dto.update.GroupUpdateDto;
import com.doubi.edit.entity.GroupEntity;
import com.doubi.edit.entity.GroupUserEntity;
import com.doubi.edit.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

  @Autowired
  private GroupDAO groupDAO;
  @Autowired
  private GroupUserDAO groupUserDAO;

  @Override
  @Transactional(rollbackFor = ServiceException.class)
  public void insert(GroupCreateDto dto) {
    GroupEntity groupEntity = BeanUtils.DtoToEntity(dto, GroupEntity.class);
    groupEntity.buildDefaultTimeStamp();
    groupEntity.setStatus(1);
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
  public void update(GroupUpdateDto dto, Long id) {
    GroupEntity entity = groupDAO.selectById(dto.getId());
    entity.buildDefaultLastTime();
    entity.setName(dto.getName());
    groupDAO.insert(entity);

  }

  @Override
  public void delete(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    if (entity.getType().equals(""))
      groupDAO.insert(entity);
  }

  @Override
  public GroupDetailDto getById(Long id) {
    return null;
  }

  @Override
  public List<GroupDto> getByUser(Long userId) {
    List<GroupEntity> entities = groupDAO.getByUserId(userId);
    List<GroupDto> dtos = new ArrayList<>(entities.size());
    for (GroupEntity entity : entities) {
      dtos.add(BeanUtils.EntityToDto(entity, GroupDto.class));
    }
    return dtos;
  }
}
