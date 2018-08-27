package com.doubi.edit.dao;

import com.doubi.edit.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * UserDAO继承基类
 */
public interface UserDAO {

  UserEntity selectById(Long id);

  int deleteById(Long id);

  int insert(UserEntity entity);

  int updateById(UserEntity entity);


  UserEntity selectByUser(String username);

  int countByPhoneOrEmail(@Param("email") String email, @Param("phone") String phone);

}