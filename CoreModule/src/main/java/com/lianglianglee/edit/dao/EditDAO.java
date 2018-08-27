package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.EditEntity;
import org.springframework.stereotype.Repository;

/**
 * EditDAO继承基类
 */
@Repository
public interface EditDAO {

  EditEntity selectById(Long id);

  int deleteById(Long id);

  int insert(EditEntity entity);

  int updateById(EditEntity entity);
}