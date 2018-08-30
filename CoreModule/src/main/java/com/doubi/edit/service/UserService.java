package com.doubi.edit.service;

import com.doubi.edit.dto.create.UserCreateDto;
import com.doubi.edit.dto.result.base.LoginDto;
import com.doubi.edit.dto.result.base.UserDto;
import com.doubi.edit.dto.update.UserPasswordDto;

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


  void insert(UserCreateDto dto);

  void checkCode(Long userId, String code);

  UserDto getById(Long id);
}
