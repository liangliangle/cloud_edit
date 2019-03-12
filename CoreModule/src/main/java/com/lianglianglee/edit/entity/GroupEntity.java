package com.lianglianglee.edit.entity;

import com.liangliagnlee.common.entity.BaseEntity;

/**
 * u_group
 *
 * @author liangliang
 */
public class GroupEntity extends BaseEntity {

  private String name;

  /**
   * 公开/私有
   */
  private String type;

  private Long userId;

  private Integer status;


  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  private void setType(String type) {
    this.type = type;
  }

  public Long getUserId() {
    return userId;
  }

  private void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getStatus() {
    return status;
  }

  private void setStatus(Integer status) {
    this.status = status;
  }

  public void change(String name, String type, Long userId, Integer status) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setName(name);
    setStatus(status);
    setType(type);
    setUserId(userId);
    buildDefaultLastTime();
  }

  public void change(String name, String type, int status) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setName(name);
    setStatus(status);
    setType(type);
    buildDefaultLastTime();
  }

  public void chageUserId(Long userId) {
    setUserId(userId);
  }
}