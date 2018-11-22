package com.lianglianglee.edit.dto.result.base;

import com.liangliagnlee.common.dto.BaseDto;

import java.util.List;

/**
 * e_edit
 *
 * @author
 */
public class EditDto extends BaseDto {


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

  /**
   * 状态
   */
  private Integer status;

  private List<EditDto> children;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<EditDto> getChildren() {
    return children;
  }

  public void setChildren(List<EditDto> children) {
    this.children = children;
  }

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