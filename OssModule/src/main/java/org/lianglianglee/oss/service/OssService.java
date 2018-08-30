package org.lianglianglee.oss.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import org.lianglianglee.oss.OssConstConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.UUID;

/**
 * OssService.
 *
 * @author liangliang
 * @date 2018年4月19日
 */
public class OssService {

  private static Logger LOGGER = LoggerFactory.getLogger(OssService.class);

  /**
   * 上传本地文件.
   *
   * @param file 文件的绝对路径.
   * @return url
   */
  public String uploadFile(File file) {

    // 创建OSSClient实例
    OSSClient ossClient = new OSSClient(OssConstConfig.getEndpoint(),
            OssConstConfig.getAccessKeyId(),
            OssConstConfig.getAccessKeySecret());
    String key = UUID.randomUUID().toString() + "." + getSuffix(file);
    PutObjectRequest request = new PutObjectRequest(OssConstConfig.getBucketName(),
            key, file);
    ossClient.putObject(request);
    // 关闭client
    ossClient.shutdown();
    return getUrl(key);
  }

  /**
   * 根据上传的key获取地址.
   *
   * @param key 文件的key
   * @return 地址
   */
  private String getUrl(String key) {
    return "http://" + OssConstConfig.getBucketName() + "." + OssConstConfig.getEndpoint()
            + "/" + key;
  }

  /**
   * 获取文件后缀.
   *
   * @param file 文件路径
   * @return 后缀
   */
  private String getSuffix(File file) {
    String fileName = file.getName();
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }
}
