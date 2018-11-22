package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.dto.result.base.EditDto;
import com.lianglianglee.edit.entity.EditEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EditDAO继承基类
 */
@Mapper
public interface EditDAO {

  EditEntity selectById(Long id);

  int deleteById(Long id);

  int insert(EditEntity entity);

  int updateById(EditEntity entity);

  List<EditDto> getByGroup(@Param("groupId") Long groupId, @Param("parentId") Long parentId);
}