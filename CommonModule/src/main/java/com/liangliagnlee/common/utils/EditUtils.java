package com.liangliagnlee.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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


}
