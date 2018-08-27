package com.doubi.edit.dto.result.base;

import com.doubi.edit.common.dto.BaseDto;

import java.io.Serializable;

/**
 * u_user
 *
 * @author liangliang
 */
public class UserDto extends BaseDto implements Serializable {

  /**
   * 用户名
   */
  private String name;

  /**
   * 邮箱
   */
  private String email;

  private String phone;
  /**
   * 密码
   */
  private String password;

  private boolean auth;

  /**
   * 状态
   */
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAuth() {
    return auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }
}