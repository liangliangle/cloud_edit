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
    GroupEntity entity = new GroupEntity();
    entity.change(dto.getName(), dto.getType(), dto.getUserId(), 1);
    groupDAO.insert(entity);
    GroupUserEntity userEntity = new GroupUserEntity();
    userEntity.change(dto.getUserId(), entity.getId(), GroupUserTypeEnum.ADMIN, dto.getUserName(), 1);
    groupUserDAO.insert(userEntity);
  }


  public void update(GroupCreateDto dto, Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, true);
    entity.change(dto.getName(), dto.getType(), 1);

    //todo 私有相关逻辑
    groupDAO.updateById(entity);
  }


  public void delete(Long id) {
    GroupEntity entity = findById(id);
    checkRole(entity, true);
    groupDAO.deleteById(id);
  }


  public GroupDto getById(Long id) {
    GroupEntity entity = groupDAO.selectById(id);
    checkRole(entity, false);
    List<GroupUserEntity> userEntities = groupUserDAO.getByGroupId(id);
    List<GroupUserDto> userDtos = new ArrayList<>(userEntities.size());
    userEntities.forEach(user -> {
      userDtos.add(BeanUtils.entityToDto(user, GroupUserDto.class));
    });

    GroupDto dto = BeanUtils.entityToDto(entity, GroupDto.class);
    dto.setUsers(userDtos);
    return dto;

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
  public void conveyGroup(Long id, Long adminUser, Long userId) {
    GroupEntity entity = findById(id);
    if (GroupTypeEnum.PRIVATE.getType().equals(entity.getType())) {
      throw new ServiceException("私有小组无法转让");
    }
    checkRole(entity, true);
    List<GroupUserEntity> oldAdmin = groupUserDAO.getByUserIdAndGroupId(id, adminUser);
    for (GroupUserEntity user : oldAdmin) {
      if (GroupUserTypeEnum.ADMIN.equals(user.getType()) && user.getStatus() == 1) {
        user.changeType(GroupUserTypeEnum.MEMBER);
        groupUserDAO.updateById(user);
        List<GroupUserEntity> newAdmin = groupUserDAO.getByUserIdAndGroupId(id, userId);
        for (GroupUserEntity newAminUser : newAdmin) {
          if (newAminUser.getStatus().equals(1)) {
            user.changeType(GroupUserTypeEnum.ADMIN);
            groupUserDAO.updateById(user);
          }
        }
      }
    }
    entity.chageUserId(userId);
    entity.buildDefaultLastTime();
    groupDAO.updateById(entity);
  }


  public void addUserByGroup(GroupUserCreateDto dto) {
    GroupEntity group = findById(dto.getGroupId());
    checkRole(group, true);
    List<GroupUserEntity> userEntities = groupUserDAO.getByUserIdAndGroupId(dto.getUserId(), dto
      .getGroupId());
    for (GroupUserEntity entity : userEntities) {
      if (entity.getStatus().equals(1)) {
        throw new ServiceException("用户已经加入小组");
      }
    }
    GroupUserEntity userEntity = new GroupUserEntity();

    UserDto user = userService.getById(dto.getUserId());
    if (null == user) {
      throw new ServiceException("用户不存在");
    }
    userEntity.change(dto.getUserId(), dto.getGroupId(), GroupUserTypeEnum.MEMBER, user.getName(), 1);
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

  public void removeUser(Long id, Long adminUser, Long userId) {
    List<GroupUserEntity> userEntities = groupUserDAO.getByUserIdAndGroupId(adminUser, id);
    userEntities.forEach(user -> {
      if (GroupUserTypeEnum.ADMIN.equals(user.getType()) && user.getStatus() == 1) {
        groupUserDAO.removeByUserIdAndGroupId(userId, id);

      }
    });
  }

  public GroupEntity findById(Long id) {
    GroupEntity group = groupDAO.selectById(id);
    if (null == group) {
      throw new ServiceException("未知小组");
    }
    return group;


  }

}
