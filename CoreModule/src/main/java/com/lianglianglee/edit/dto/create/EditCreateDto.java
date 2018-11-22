package com.lianglianglee.edit.dto.create;

import io.swagger.annotations.ApiModelProperty;

public class EditCreateDto {


  private String title;
  /**
   * 小组ID
   */
  @ApiModelProperty("小组ID")
  private Long groupId;

  /**
   * 父ID
   */
  @ApiModelProperty("父页面ID")
  private Long parentId;

  @ApiModelProperty("正文")
  private String content;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }


  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
