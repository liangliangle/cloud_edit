package com.doubi.edit.dto.update;

import com.doubi.edit.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

public class GroupConveyDto extends BaseDto {

  @ApiModelProperty("新管理员ID")
  private Long newUserId;
  @ApiModelProperty("旧管理员ID")
  private Long oldUserId;

  public Long getNewUserId() {
    return newUserId;
  }

  public void setNewUserId(Long newUserId) {
    this.newUserId = newUserId;
  }

  public Long getOldUserId() {
    return oldUserId;
  }

  public void setOldUserId(Long oldUserId) {
    this.oldUserId = oldUserId;
  }
}
