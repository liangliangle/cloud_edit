package com.doubi.edit.dao;

import com.doubi.edit.entity.EditInfoEntity;

/**
 * EditInfoDAO继承基类
 */
public interface EditInfoDAO {
  EditInfoEntity selectById(Long id);

  int deleteById(Long id);

  int updateById(EditInfoEntity entity);

  int insert(EditInfoEntity entity);
}