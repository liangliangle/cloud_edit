package com.doubi.edit.dto.result.base;

import com.doubi.edit.common.dto.BaseDto;

import java.io.Serializable;

/**
 * edit-log
 * @author 
 */
public class EditLogDto extends BaseDto {


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