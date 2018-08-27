package com.lianglianglee.edit.entity;

import com.lianglianglee.edit.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * e_edit_info
 * @author 
 */
public class EditInfoEntity  extends BaseEntity implements Serializable {

    private Long editId;

    private Integer type;

    private Integer status;

    /**
     * 正文
     */
    private String info;

    private static final long serialVersionUID = 1L;


    public Long getEditId() {
        return editId;
    }

    public void setEditId(Long editId) {
        this.editId = editId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}