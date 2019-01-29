package com.liangliagnlee.common.dto;

import io.swagger.annotations.ApiModelProperty;

public class BaseDto {
  @ApiModelProperty("主键 id")
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
