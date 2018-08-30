package com.doubi.edit.service.impl;

import com.doubi.edit.common.exception.ServiceException;
import com.doubi.edit.common.utils.BeanUtils;
import com.doubi.edit.dao.GroupDAO;
import com.doubi.edit.dao.GroupUserDAO;
import com.doubi.edit.dto.create.GroupCreateDto;
import com.doubi.edit.dto.result.GroupDetailDto;
import com.doubi.edit.dto.result.base.GroupDto;
import com.doubi.edit.dto.result.base.GroupUserDto;
import com.doubi.edit.dto.update.GroupUpdateDto;
import com.doubi.edit.entity.GroupEntity;
import com.doubi.edit.entity.GroupUserEntity;
import com.doubi.edit.interceptor.HttpContext;
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
    GroupEntity groupEntity = BeanUtils.dtoToEntity(dto, GroupEntity.class);
    groupEntity.buildDefaultTimeStamp();
    groupEntity.setStatus(1);
    groupDAO.insert(groupEntity);
    GroupUserEntity entity = new GroupUserEntity();
    entity.setGroupId(groupEntity.getId());
    entity.setUserId(dto.getUserId());
    entity.setType("管理");
    entity.setStatus(1);
    entity.setUserName(dto.getUserName());
    entity.buildDefaultTimeStamp();
    groupUserDAO.insert(entity);
  }

  @Override
  public void update(GroupUpdateDto dto, Long id) {
    GroupEntity entity = groupDAO.selectById(dto.getId());
    checkRole(entity, true);
    entity.buildDefaultLastTime();
    entity.setName(dto.getName());
    groupDAO.updateById(entity);
  }

  @Override
  public void delete(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, true);
    groupDAO.deleteById(id);
  }

  @Override
  public GroupDetailDto getById(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, false);
    List<GroupUserEntity> userEntities = groupUserDAO.getByGroupId(id);
    GroupDetailDto detailDto = new GroupDetailDto();
    detailDto.setDto(BeanUtils.entityToDto(entity, GroupDto.class));
    List<GroupUserDto> dtos = new ArrayList<GroupUserDto>(userEntities.size());
    for (GroupUserEntity user : userEntities) {
      dtos.add(BeanUtils.entityToDto(user, GroupUserDto.class));
    }
    detailDto.setUsers(dtos);
    return detailDto;
  }

  @Override
  public List<GroupDto> getByUser(Long userId) {
    List<GroupEntity> entities = groupDAO.getByUserId(userId);
    List<GroupDto> dtos = new ArrayList<>(entities.size());
    for (GroupEntity entity : entities) {
      dtos.add(BeanUtils.entityToDto(entity, GroupDto.class));
    }
    return dtos;
  }

  private void checkRole(GroupEntity entity, Boolean update) {
    if (entity == null) {
      throw new ServiceException("笔记不存在");
    }
    Long userId = HttpContext.getContext().getUserId();
    if (!userId.equals(entity.getUserId())) {
      throw new ServiceException("笔记不存在");
    }
    if (update && entity.getName().equals("默认笔记")) {
      throw new ServiceException("默认笔记不允许操作");
    }
  }

}
