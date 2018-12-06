package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.GroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GroupDAO继承基类
 */
@Mapper
public interface GroupDAO {
  GroupEntity selectById(Long id);

  List<GroupEntity> getByIds(@Param("ids") List<Long> ids);

  int deleteById(Long id);

  int insert(GroupEntity entity);

  int updateById(GroupEntity entity);


  List<GroupEntity> getByUserId(Long userId);

}