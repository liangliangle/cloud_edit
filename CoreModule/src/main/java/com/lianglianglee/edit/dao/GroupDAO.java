package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.GroupEntity;
import org.springframework.stereotype.Repository;

/**
 * GroupDAO继承基类
 */
public interface GroupDAO {
  GroupEntity selectById(Long id);

  int deleteById(Long id);

  int insert(GroupEntity entity);

  int updateById(GroupEntity entity);

}