package com.lianglianglee.edit.entity;

import com.liangliagnlee.common.entity.BaseEntity;

/**
 * e_edit_info
 *
 * @author
 */
public class EditInfoEntity extends BaseEntity {

  private Long editId;

  private Integer type;

  private Integer status;

  /**
   * 正文
   */
  private String info;


  public Long getEditId() {
    return editId;
  }

  private void setEditId(Long editId) {
    this.editId = editId;
  }

  public Integer getType() {
    return type;
  }

  private void setType(Integer type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  private void setStatus(Integer status) {
    this.status = status;
  }

  public String getInfo() {
    return info;
  }

  private void setInfo(String info) {
    this.info = info;
  }

  public void change(Long editId, Integer type, Integer status, String info) {
    if (getId() == null) {
      buildDefaultTimeStamp();
    }
    setEditId(editId);
    setInfo(info);
    setStatus(status);
    setType(type);
    buildDefaultLastTime();
  }
}