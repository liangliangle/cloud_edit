package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.EditLogEntity;
import org.springframework.stereotype.Repository;

/**
 * EditLogDAO继承基类
 */
@Repository
public interface EditLogDAO {

  EditLogEntity selectById(Long id);

  int deleteById(Long id);

  int insert(EditLogEntity entity);

  int updateById(EditLogEntity entity);
}