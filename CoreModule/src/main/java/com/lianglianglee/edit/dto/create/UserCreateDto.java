package com.lianglianglee.edit.dto.create;

import com.liangliagnlee.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

public class UserCreateDto extends BaseDto {

  /**
   * 用户名
   */
  @ApiModelProperty("用户名称")
  private String name;

  /**
   * 邮箱
   */
  @ApiModelProperty("密码")
  private String password;
  @ApiModelProperty("邮箱")
  private String email;
  @ApiModelProperty("手机号")
  private String phone;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
