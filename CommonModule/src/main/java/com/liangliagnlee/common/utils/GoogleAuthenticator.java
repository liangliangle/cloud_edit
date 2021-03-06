package com.liangliagnlee.common.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


//Google  Authenticator

// 只从google出了双重身份验证后，就方便了大家，等同于有了google一个级别的安全，但是我们该怎么使用google authenticator (双重身份验证)，


//测试方法：
//
//1、执行测试代码中的“genSecret”方法，将生成一个KEY（用户为testuser），URL打开是一张二维码图片。
//
//2、在手机中下载“GOOGLE身份验证器”。
//
//3、在身份验证器中配置账户，输入账户名（第一步中的用户testuser）、密钥（第一步生成的KEY），选择基于时间。

//4、运行authcode方法将key和要测试的验证码带进去（codes，key），就可以知道是不是正确的秘钥了！返回值布尔

//main我就不写了大家~~因为这个可以当做util工具直接调用就行了
//

public class GoogleAuthenticator {

  // taken from Google pam docs - we probably don't need to mess with theseFileController
  private static final int SECRET_SIZE = 10;

  private static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";

  private static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

  private static Logger logger = Logger.getLogger(GoogleAuthenticator.class);

  private static int window_size = 3; // default 3 - max 17 (from google docs)最多可偏移的时间

  public void setWindowSize(int s) {
    if (s >= 1 && s <= 17)
      window_size = s;
  }


  public static Boolean authcode(String codes, String savedSecret) {
    // enter the code shown on device. Edit this and run it fast before the
    // code expires!
    long code = Long.parseLong(codes);
    long t = System.currentTimeMillis();
    GoogleAuthenticator ga = new GoogleAuthenticator();
    ga.setWindowSize(window_size); // should give 5 * 30 seconds of grace...
    boolean r = ga.check_code(savedSecret, code, t);
    return r;
  }


  public static String getUrl(String secret, String name) {
    //TODO  笔记名称
    return GoogleAuthenticator.getQRBarcodeURL("云笔记",
      name, secret);
  }

  public static String generateSecretKey() {
    SecureRandom sr = null;
    try {
      sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
      sr.setSeed(Base64.decodeBase64(SEED));
      byte[] buffer = sr.generateSeed(SECRET_SIZE);
      Base32 codec = new Base32();
      byte[] bEncodedKey = codec.encode(buffer);
      String encodedKey = new String(bEncodedKey);
      return encodedKey;
    } catch (NoSuchAlgorithmException e) {
      // should never occur... configuration error
    }
    return null;
  }


  public static String getQRBarcodeURL(String user, String host, String secret) {
    return "otpauth://totp/" + user + ":" + host + "?secret=" + secret;
  }


  private boolean check_code(String secret, long code, long timeMsec) {
    Base32 codec = new Base32();
    byte[] decodedKey = codec.decode(secret);
    // convert unix msec time into a 30 second "window"
    // this is per the TOTP spec (see the RFC for details)
    long t = (timeMsec / 1000L) / 30L;
    // Window is used to check codes generated in the near past.
    // You can use this value to tune how far you're willing to go.
    for (int i = -window_size; i <= window_size; ++i) {
      long hash;
      try {
        hash = verify_code(decodedKey, t + i);
      } catch (Exception e) {
        // Yes, this is bad form - but
        // the exceptions thrown would be rare and a static configuration problem
        e.printStackTrace();
        throw new RuntimeException(e.getMessage());
        //return false;
      }
      if (hash == code) {
        return true;
      }
    }
    // The validation code is invalid.
    return false;
  }

  private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
    byte[] data = new byte[8];
    long value = t;
    for (int i = 8; i-- > 0; value >>>= 8) {
      data[i] = (byte) value;
    }
    SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
    Mac mac = Mac.getInstance("HmacSHA1");
    mac.init(signKey);
    byte[] hash = mac.doFinal(data);
    int offset = hash[20 - 1] & 0xF;
    // We're using a long because Java hasn't got unsigned int.
    long truncatedHash = 0;
    for (int i = 0; i < 4; ++i) {
      truncatedHash <<= 8;
      // We are dealing with signed bytes:
      // we just keep the first byte.
      truncatedHash |= (hash[offset + i] & 0xFF);
    }
    truncatedHash &= 0x7FFFFFFF;
    truncatedHash %= 1000000;
    return (int) truncatedHash;
  }

  public static void main(String args[]) {
    //String key = GoogleAuthenticator.generateSecretKey();
    //logger.info(GoogleAuthenticator.getUrl(key, "liangliang"));
    logger.info(System.currentTimeMillis());
    logger.info(GoogleAuthenticator.authcode("337570", "MSIZ34OR6BFXWMGT"));
    logger.info(System.currentTimeMillis());
  }
}


