package com.lianglianglee.edit.dto.create;

import com.liangliagnlee.common.utils.Validation;
import io.swagger.annotations.ApiModelProperty;

public class EditCreateDto {

  @Validation(notNull = true, value = "标题")
  private String title;
  /**
   * 小组ID
   */
  @Validation(notNull = true, value = "小组")
  @ApiModelProperty("小组ID")
  private Long groupId;

  /**
   * 父ID
   */

  @Validation(notNull = true, value = "父页面")
  @ApiModelProperty("父页面ID")
  private Long parentId;
  //@Validation(notNull = true)
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
