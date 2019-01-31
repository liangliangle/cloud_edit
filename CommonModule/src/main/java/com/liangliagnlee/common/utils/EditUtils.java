package com.liangliagnlee.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hekeji on 17/5/9.
 */
public class EditUtils {

  private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * MD5 encode.
   */
  public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    return Base64Utils.encode(md5.digest(str.getBytes("utf-8")));
  }

  public static Timestamp getCurrentTimestamp() {
    return new Timestamp(System.currentTimeMillis());
  }

  /**
   * 根据传入时间格式，转换为 sql timesamp.
   *
   * @param timeString 只接受 yyyy-MM-dd HH:mm:ss 格式
   */
  public static Timestamp getTimestamp(String timeString) throws ParseException {
    if (StringUtils.isEmpty(timeString)) {
      return null;
    }
    return new Timestamp(format.parse(timeString).getTime());
  }

  public static String getDateStringFromTimeStamp(Timestamp timestamp) {
    return format.format(timestamp);
  }

  public static String getFileKey(String name, String imageFolder) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    String prefix = "/" + format.format(new Date());
    if (!new File(imageFolder + prefix).exists()) {
      new File(imageFolder + prefix).mkdirs();
    }
    name = StringUtils.trimToNull(name);
    if (name == null) {
      return prefix + "/" + UUID.UU32() + "." + null;
    } else {
      name = name.replace('\\', '/');
      name = name.substring(name.lastIndexOf("/") + 1);
      int index = name.lastIndexOf(".");
      String ext = null;
      if (index >= 0) {
        ext = org.apache.commons.lang3.StringUtils.trimToNull(name.substring(index + 1));
      }
      return prefix + "/" + UUID.UU32() + "." + (ext == null ? null : (ext));
    }
  }

  /**
   * 获取保存文件的位置,jar所在目录的路径
   *
   * @return
   */
  public static String getUploadFilePath() {
    String path = EditUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    path = path.substring(1, path.length());
    try {
      path = java.net.URLDecoder.decode(path, "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    Integer lastIndex = path.lastIndexOf("/") + 1;
    File file = new File("");
    return file.getAbsolutePath() + "/";
  }

}
