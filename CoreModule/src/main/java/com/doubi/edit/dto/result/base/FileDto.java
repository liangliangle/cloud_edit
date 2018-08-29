package com.doubi.edit.dto.result.base;

import com.doubi.edit.common.dto.BaseDto;

import java.io.Serializable;

/**
 * e_file
 * @author 
 */
public class FileDto extends BaseDto implements Serializable {


    private Long editId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String url;

    private String status;


    public Long getEditId() {
        return editId;
    }

    public void setEditId(Long editId) {
        this.editId = editId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}