package com.doubi.edit.dao;

import com.doubi.edit.entity.GroupUserEntity;

/**
 * GroupUserDAO继承基类
 */
public interface GroupUserDAO {

  GroupUserEntity selectById(Long id);

  int deleteById(Long id);

  int insert(GroupUserEntity entity);

  int updateById(GroupUserEntity entity);
}