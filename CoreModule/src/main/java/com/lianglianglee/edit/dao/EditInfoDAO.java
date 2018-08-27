package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.EditInfoEntity;
import org.springframework.stereotype.Repository;

/**
 * EditInfoDAO继承基类
 */
public interface EditInfoDAO {
  EditInfoEntity selectById(Long id);

  int deleteById(Long id);

  int updateById(EditInfoEntity entity);

  int insert(EditInfoEntity entity);
}