package com.lianglianglee.edit.entity;

import com.liangliagnlee.common.entity.BaseEntity;

/**
 * e_file
 *
 * @author
 */
public class FileEntity extends BaseEntity {


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

  private void setEditId(Long editId) {
    this.editId = editId;
  }

  public String getFileName() {
    return fileName;
  }

  private void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getUrl() {
    return url;
  }

  private void setUrl(String url) {
    this.url = url;
  }

  public String getStatus() {
    return status;
  }

  private void setStatus(String status) {
    this.status = status;
  }

  public void change(Long editId, String fileName, String url, String status) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setEditId(editId);
    setFileName(fileName);
    setStatus(status);
    setUrl(url);
    buildDefaultLastTime();
  }
}