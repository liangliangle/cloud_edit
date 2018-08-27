package com.lianglianglee.edit.entity;

import com.lianglianglee.edit.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * u_group_user liangliang
 * @author 
 */
public class GroupUserEntity extends BaseEntity implements Serializable {

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

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}