package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.FileEntity;

/**
 * FileDAO继承基类
 */
public interface FileDAO  {

  FileEntity selectById(Long id);


  int deleteById(Long id);

  int insert(FileEntity entity);

  int updateById(FileEntity entity);
}