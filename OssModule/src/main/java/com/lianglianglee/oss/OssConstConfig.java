package com.kcss.oss;


import com.lianglianglee.oss.entity.OssConfig;

/**
 * OSS配置.
 *
 * @author liangliang
 * @date 2018年5月3日
 */
public class OssConstConfig {

  private static OssConfig config;

  public static OssConfig getConfig() {
    return config;
  }

  public static void setConfig(OssConfig config) {
    OssConstConfig.config = config;
  }


  public static String getAccessKeyId() {
    return config.getAccessKeyId();
  }


  public static String getAccessKeySecret() {
    return config.getAccessKeySecret();
  }


  public static String getEndpoint() {
    return config.getEndpoint();
  }

  public static String getBucketName() {
    return config.getBucketName();
  }


}
