package com.lianglianglee.edit.entity;

import com.lianglianglee.edit.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

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

    private static final long serialVersionUID = 1L;


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