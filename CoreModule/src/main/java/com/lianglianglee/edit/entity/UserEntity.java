package com.lianglianglee.edit.entity;

import com.liangliagnlee.common.entity.BaseEntity;

/**
 * u_user
 *
 * @author liangliang
 */
public class UserEntity extends BaseEntity {

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

  /**
   * 安全密钥
   */
  private String secret;

  /**
   * 状态
   */
  private Integer status;


  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  private void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  private void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  private void setPassword(String password) {
    this.password = password;
  }

  public String getSecret() {
    return secret;
  }

  private void setSecret(String secret) {
    this.secret = secret;
  }

  public Integer getStatus() {
    return status;
  }

  private void setStatus(Integer status) {
    this.status = status;
  }

  public void change(String name, String email, String phone, String password, String secret, Integer status) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setEmail(email);
    setName(name);
    setPassword(password);
    setPhone(phone);
    setSecret(secret);
    setStatus(status);
    buildDefaultLastTime();
  }

  public void change(String name, String email, String phone) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setEmail(email);
    setName(name);
    setPhone(phone);
    buildDefaultLastTime();
  }

  public void changePassword(String password) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setPassword(password);
    buildDefaultLastTime();
  }


  public void changeSecret(String secret) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setSecret(secret);
    buildDefaultLastTime();
  }
}