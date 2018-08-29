package com.doubi.edit.entity;

import com.doubi.edit.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * edit-log
 * @author 
 */
public class EditLogEntity  extends BaseEntity implements Serializable {


    /**
     * 正文
     */
    private String content;

    /**
     * 类型
     */
    private String type;

    private Integer status;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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