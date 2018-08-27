package com.lianglianglee.edit.dto.result;

import com.lianglianglee.edit.common.dto.BaseDto;
import com.lianglianglee.edit.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * edit-log
 * @author 
 */
public class EditLogDto extends BaseDto implements Serializable {


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