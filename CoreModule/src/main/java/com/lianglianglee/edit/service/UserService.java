package com.lianglianglee.edit.service;

import com.lianglianglee.edit.dto.result.LoginDto;
import com.lianglianglee.edit.dto.result.UserDto;
import com.lianglianglee.edit.dto.update.UserPasswordDto;

public interface UserService {

  LoginDto login(String userName, String password);


  void update(UserDto userDto);

  void updatePassword(UserPasswordDto dto);

  /**
   * 获取用户二次验证二维码
   *
   * @param userId
   * @return
   */
  String getCodeImg(Long userId);


  void insert(UserDto dto);

  void checkCode(Long userId, String code);
}
