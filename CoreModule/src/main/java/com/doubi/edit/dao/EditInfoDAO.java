package com.doubi.edit.dao;

import com.doubi.edit.entity.EditInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * EditInfoDAO继承基类
 */
@Mapper
public interface EditInfoDAO {
  EditInfoEntity selectById(Long id);

  int deleteById(Long id);

  int updateById(EditInfoEntity entity);

  int insert(EditInfoEntity entity);
}