package com.lianglianglee.edit.dto.update;

import com.lianglianglee.edit.common.dto.BaseDto;

public class UserPasswordDto extends BaseDto {

  private String oldPassword;
  private String newPassword;

  private String code;

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
