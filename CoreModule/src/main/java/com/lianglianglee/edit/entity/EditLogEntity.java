package com.lianglianglee.edit.entity;

import com.liangliagnlee.common.entity.BaseEntity;

/**
 * edit-log
 *
 * @author
 */
public class EditLogEntity extends BaseEntity {


  /**
   * 正文
   */
  private Long infoId;

  private Long editId;

  /**
   * 类型
   */
  private String type;

  private Integer status;

  public Long getInfoId() {
    return infoId;
  }

  private void setInfoId(Long infoId) {
    this.infoId = infoId;
  }

  public Long getEditId() {
    return editId;
  }

  private void setEditId(Long editId) {
    this.editId = editId;
  }

  public String getType() {
    return type;
  }

  private void setType(String type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  private void setStatus(Integer status) {
    this.status = status;
  }

  public void change(Long infoId, Long editId, String type, Integer status) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setEditId(editId);
    setInfoId(infoId);
    setStatus(status);
    setType(type);
    buildDefaultLastTime();
  }
}