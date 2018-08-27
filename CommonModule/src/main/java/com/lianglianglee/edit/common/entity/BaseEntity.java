package com.lianglianglee.edit.common.entity;

import com.lianglianglee.edit.common.utils.EditUtils;

import java.io.Serializable;
import java.util.Date;


public class BaseEntity implements Serializable {

  private Long id;

  private Date createTime;

  private Date lastUpdateTime;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public void buildDefaultTimeStamp() {
    setCreateTime(EditUtils.getCurrentTimestamp());
    setLastUpdateTime(EditUtils.getCurrentTimestamp());
  }

  public void buildDefaultLastTime(){
    setLastUpdateTime(EditUtils.getCurrentTimestamp());
  }
}
