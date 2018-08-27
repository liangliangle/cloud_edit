package com.doubi.edit.entity;

import com.doubi.edit.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * u_group
 * @author  liangliang
 */
public class GroupEntity extends BaseEntity implements Serializable {

    private String name;

    /**
     * 公开/私有
     */
    private String type;

    private Long userId;

    private Integer status;

    private static final long serialVersionUID = 1L;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}