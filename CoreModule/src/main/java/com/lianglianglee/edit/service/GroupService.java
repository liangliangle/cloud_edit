package com.lianglianglee.edit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.liangliagnlee.common.exception.ServiceException;
import com.liangliagnlee.common.interceptor.HttpContext;
import com.liangliagnlee.common.utils.BeanUtils;
import com.lianglianglee.edit.dao.GroupDAO;
import com.lianglianglee.edit.dao.GroupUserDAO;
import com.lianglianglee.edit.dto.create.GroupCreateDto;
import com.lianglianglee.edit.dto.create.GroupUserCreateDto;
import com.lianglianglee.edit.dto.result.base.GroupDto;
import com.lianglianglee.edit.dto.result.base.GroupUserDto;
import com.lianglianglee.edit.dto.result.base.UserDto;
import com.lianglianglee.edit.entity.GroupEntity;
import com.lianglianglee.edit.entity.GroupUserEntity;
import com.lianglianglee.edit.enums.GroupTypeEnum;
import com.lianglianglee.edit.enums.GroupUserTypeEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

  @Autowired
  private GroupDAO groupDAO;
  @Autowired
  private GroupUserDAO groupUserDAO;
  @Autowired
  private UserService userService;


  @Transactional(rollbackFor = ServiceException.class)
  public void insert(GroupCreateDto dto) {
    GroupEntity groupEntity = BeanUtils.dtoToEntity(dto, GroupEntity.class);
    groupEntity.buildDefaultTimeStamp();
    groupEntity.setStatus(1);
    groupDAO.insert(groupEntity);
    GroupUserEntity entity = new GroupUserEntity();
    entity.setGroupId(groupEntity.getId());
    entity.setUserId(dto.getUserId());
    entity.setType(GroupUserTypeEnum.ADMIN.getType());
    entity.setStatus(1);
    entity.setUserName(dto.getUserName());
    entity.buildDefaultTimeStamp();
    groupUserDAO.insert(entity);
  }


  public void update(GroupCreateDto dto, Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, true);
    entity.buildDefaultLastTime();
    entity.setName(dto.getName());
    entity.setType(dto.getType());
    //todo 私有相关逻辑
    groupDAO.updateById(entity);
  }


  public void delete(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, true);
    groupDAO.deleteById(id);
  }


  public GroupDto getById(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, false);
    // todu 用户List<GroupUserEntity> userEntities = groupUserDAO.getByGroupId(id);
    return BeanUtils.entityToDto(entity, GroupDto.class);

  }


  public List<GroupDto> getByUser(Long userId) {
    List<GroupUserEntity> groupUsers = groupUserDAO.getByUserId(userId);
    List<Long> ids = groupUsers.stream().map(GroupUserEntity::getGroupId).collect(Collectors.toList());
    List<GroupEntity> entities = groupDAO.getByIds(ids);
    List<GroupDto> dtos = new ArrayList<>(entities.size());
    for (GroupEntity entity : entities) {
      dtos.add(BeanUtils.entityToDto(entity, GroupDto.class));
    }
    return dtos;
  }


  @Transactional(rollbackFor = ServiceException.class)
  public void conveyGroup(Long id, Long userId) {
    GroupEntity entity = groupDAO.selectById(id);
    if (GroupTypeEnum.PRIVATE.getType().equals(entity.getType())) {
      throw new ServiceException("私有小组无法转让");
    }
    checkRole(entity, true);
    List<GroupUserEntity> oldAdmin = groupUserDAO.getByUserIdAndGroupId(id, entity.getUserId());
    for (GroupUserEntity user : oldAdmin) {
      if (user.getStatus().equals(1)) {
        user.setType(GroupUserTypeEnum.MEMBER.getType());
        user.buildDefaultLastTime();
        groupUserDAO.updateById(user);
      }
    }
    List<GroupUserEntity> newAdmin = groupUserDAO.getByUserIdAndGroupId(id, userId);
    for (GroupUserEntity user : newAdmin) {
      if (user.getStatus().equals(1)) {
        user.setType(GroupUserTypeEnum.ADMIN.getType());
        user.buildDefaultLastTime();
        groupUserDAO.updateById(user);
      }
    }
    entity.setUserId(userId);
    entity.buildDefaultLastTime();
    groupDAO.updateById(entity);
  }


  public void addUserByGroup(GroupUserCreateDto dto) {
    GroupEntity group = groupDAO.selectById(dto.getGroupId());
    checkRole(group, true);
    List<GroupUserEntity> userEntities = groupUserDAO.getByUserIdAndGroupId(dto.getUserId(), dto
      .getGroupId());
    for (GroupUserEntity entity : userEntities) {
      if (entity.getStatus().equals(1)) {
        throw new ServiceException("用户已经加入小组");
      }
    }
    GroupUserEntity userEntity = new GroupUserEntity();
    userEntity.setType(GroupUserTypeEnum.MEMBER.getType());
    userEntity.setGroupId(dto.getGroupId());
    userEntity.setStatus(1);
    userEntity.setUserId(dto.getUserId());
    UserDto user = userService.getById(dto.getUserId());
    if (null == user) {
      throw new ServiceException("用户不存在");
    }
    userEntity.setUserName(user.getName());
    groupUserDAO.insert(userEntity);
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

  public List<GroupUserDto> getUsers(Long id) {
    List<GroupUserEntity> userEntities = groupUserDAO.getByGroupId(id);
    List<GroupUserDto> dtos = new ArrayList<GroupUserDto>(userEntities.size());
    for (GroupUserEntity user : userEntities) {
      dtos.add(BeanUtils.entityToDto(user, GroupUserDto.class));
    }
    return dtos;
  }

  public void removeUser(Long id, Long userId) {
    groupUserDAO.removeByUserIdAndGroupId(userId, id);
  }

  public void submitToGroup(Long id, Boolean status) {
    GroupUserEntity entity = groupUserDAO.selectById(id);
    if (status) {
      entity.setStatus(1);
    } else {
      entity.setStatus(0);
    }
    entity.buildDefaultLastTime();
    groupUserDAO.updateById(entity);
  }

}
