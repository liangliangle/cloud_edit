package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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