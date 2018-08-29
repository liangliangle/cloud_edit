package com.doubi.edit.dto.update;

import com.doubi.edit.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

public class GroupUpdateDto extends BaseDto {

    @ApiModelProperty("小组名称")
    private String name;
    @ApiModelProperty("创建人")
    private Long userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
