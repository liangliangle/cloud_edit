package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.EditLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * EditLogDAO继承基类
 */
@Mapper
public interface EditLogDAO {

  EditLogEntity selectById(Long id);

  int deleteById(Long id);

  int insert(EditLogEntity entity);

  int updateById(EditLogEntity entity);

}