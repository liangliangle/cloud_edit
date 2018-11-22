package com.lianglianglee.edit.dto.create;

import com.liangliagnlee.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

public class GroupCreateDto extends BaseDto {


  @ApiModelProperty("小组名称")
  private String name;

  /**
   * 公开/私有
   */
  @ApiModelProperty("小组类型，private,public")
  private String type;
  @ApiModelProperty("创建人")
  private Long userId;
  @ApiModelProperty("用户名")
  private String userName;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
