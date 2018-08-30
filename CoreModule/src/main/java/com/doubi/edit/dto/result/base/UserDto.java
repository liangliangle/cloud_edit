package com.doubi.edit.dto.result.base;

import com.doubi.edit.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * u_user
 *
 * @author liangliang
 */
public class UserDto extends BaseDto {

  /**
   * 用户名
   */
  @ApiModelProperty("用户名称")
  private String name;

  /**
   * 邮箱
   */
  @ApiModelProperty("邮箱")
  private String email;
  @ApiModelProperty("手机号")
  private String phone;

  @ApiModelProperty("是否二次验证")
  private boolean auth;

  /**
   * 状态
   */
  @ApiModelProperty("状态，1：正常，2异常")
  private Integer status;


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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  public boolean isAuth() {
    return auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }
}