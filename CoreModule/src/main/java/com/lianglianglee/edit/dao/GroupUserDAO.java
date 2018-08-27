package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.GroupEntity;
import com.lianglianglee.edit.entity.GroupUserEntity;
import org.springframework.stereotype.Repository;

/**
 * GroupUserDAO继承基类
 */
public interface GroupUserDAO {

  GroupUserEntity selectById(Long id);

  int deleteById(Long id);

  int insert(GroupUserEntity entity);

  int updateById(GroupUserEntity entity);
}