package com.doubi.edit.dao;

import com.doubi.edit.entity.GroupUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GroupUserDAO继承基类
 */
@Mapper
public interface GroupUserDAO {

  GroupUserEntity selectById(Long id);

  int deleteById(Long id);

  int insert(GroupUserEntity entity);

  int updateById(GroupUserEntity entity);


  List<GroupUserEntity> getByGroupId(Long groupId);
}