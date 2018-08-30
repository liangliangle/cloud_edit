package com.doubi.edit.entity;

import com.doubi.edit.common.entity.BaseEntity;

/**
 * e_edit
 *
 * @author
 */
public class EditEntity extends BaseEntity {


  /**
   * 创建人
   */
  private Long userId;

  /**
   * 小组ID
   */
  private Long groupId;

  /**
   * 用户名
   */
  private String userName;

  /**
   * 父ID
   */
  private Long parentId;

  /**
   * 状态
   */
  private Integer status;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}