package com.lianglianglee.edit.enums;

/**
 * Created by liliang on 2018/2/5.
 *
 * @author liliang
 * 活动类型
 */
public enum AcitvityType {

  /**
   * 内部活动
   * 院级活动
   * 校级活动
   * 其他活动
   */
  SCHOOLE("校级活动"), COLLEGE("院级活动"), PRIVATE("内部活动"), STATE("其他活动");


  private String type;

  private AcitvityType(String type) {
    this.type = type;
  }

  public static boolean verification(String type) {
    for (AcitvityType acitvityType : AcitvityType.values()) {
      if (acitvityType.type.equals(type)) {
        return true;
      }
    }
    return false;

  }

  public String getType() {
    return type;
  }
}
