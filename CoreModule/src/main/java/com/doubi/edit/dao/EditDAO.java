package com.doubi.edit.dao;

import com.doubi.edit.entity.EditEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * EditDAO继承基类
 */
@Mapper
public interface EditDAO {

  EditEntity selectById(Long id);

  int deleteById(Long id);

  int insert(EditEntity entity);

  int updateById(EditEntity entity);
}