package com.lianglianglee.edit.entity;

import com.liangliagnlee.common.entity.BaseEntity;
import com.lianglianglee.edit.enums.GroupUserTypeEnum;

/**
 * u_group_user liangliang
 *
 * @author
 */
public class GroupUserEntity extends BaseEntity {

  /**
   * 用户ID
   */
  private Long userId;

  /**
   * 小组ID
   */
  private Long groupId;

  /**
   * 类型
   */
  private String type;

  private String userName;


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

  public String getType() {
    return type;
  }

  private void setType(String type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  private void setStatus(Integer status) {
    this.status = status;
  }

  public String getUserName() {
    return userName;
  }

  private void setUserName(String userName) {
    this.userName = userName;
  }

  public void change(Long userId, Long groupId, GroupUserTypeEnum type, String userName, Integer status) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setGroupId(groupId);
    setStatus(status);
    setType(type.getType());
    setUserId(userId);
    setUserName(userName);
    buildDefaultLastTime();
  }

  public void changeType(GroupUserTypeEnum type) {
    setType(type.getType());
  }

}