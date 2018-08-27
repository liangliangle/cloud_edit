package com.doubi.edit.dao;

import com.doubi.edit.entity.GroupEntity;

/**
 * GroupDAO继承基类
 */
public interface GroupDAO {
  GroupEntity selectById(Long id);

  int deleteById(Long id);

  int insert(GroupEntity entity);

  int updateById(GroupEntity entity);

}