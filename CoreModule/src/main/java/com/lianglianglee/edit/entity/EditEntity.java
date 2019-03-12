package com.lianglianglee.edit.entity;

import com.liangliagnlee.common.entity.BaseEntity;

/**
 * e_edit
 *
 * @author
 */
public class EditEntity extends BaseEntity {


  private String title;
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

  public String getTitle() {
    return title;
  }

  private void setTitle(String title) {
    this.title = title;
  }

  /**
   * 状态
   */

  private Integer status;

  public Long getUserId() {
    return userId;
  }

  private void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getGroupId() {
    return groupId;
  }

  private void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public String getUserName() {
    return userName;
  }

  private void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getParentId() {
    return parentId;
  }

  private void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getStatus() {
    return status;
  }

  private void setStatus(Integer status) {
    this.status = status;
  }

  public void change(String title, Long userId, Long groupId, String userName, Long parentId, Integer status) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setGroupId(groupId);
    setParentId(parentId);
    setStatus(status);
    setTitle(title);
    setUserId(userId);
    setUserName(userName);
    buildDefaultLastTime();
  }

  public void change(String title, Long groupId, Long parentId) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setGroupId(groupId);
    setParentId(parentId);
    setTitle(title);
    buildDefaultLastTime();
  }
}