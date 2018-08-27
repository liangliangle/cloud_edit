package com.doubi.edit.dto.create;

import com.doubi.edit.common.dto.BaseDto;
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
}
