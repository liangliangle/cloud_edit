package com.doubi.edit.common.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA 加密实现, 部分代码参考自网络 Created by hekeji on 16/2/17.
 * <p>
 * <ul>
 * <li>私钥加密,私钥解密</li>
 * <li>公钥加密,公钥解密</li>
 * <li>私钥加密,公钥解密/li>
 * </ul>
 * </p>
 *
 * @since 1.0
 */
public class RsaUtils {

  /**
   * 加密算法RSA.
   */
  public static final String KEY_ALGORITHM = "RSA";

  /**
   * 签名算法.
   */
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

  /**
   * 获取公钥的key.
   */
  private static final String PUBLIC_KEY = "MASS_OAUTH_PUBLIC";

  /**
   * 获取私钥的key.
   */
  private static final String PRIVATE_KEY = "MASS_OAUTH_PRIVATE";

  /**
   * RSA最大加密明文大小.
   */
  private static final int MAX_ENCRYPT_BLOCK = 117;

  /**
   * RSA最大解密密文大小.
   */
  private static final int MAX_DECRYPT_BLOCK = 128;


  public static final String PUBLIC_RSA_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKCZSUwgGo5sT2rTjv"
          + "f9pl25Sap+8VktMvaCM/yunjInWvvt2fb2HZishCjdJobBSzeb4RGYXdx5+QJhRaUqc6cCAwEAAQ==";


  public static final String PRIVATE_RSA_KEY =
          "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAoJlJTCAajmxPatOO9/2m"
                  + "XblJqn7xWS0y9oIz/K6eMida++3Z9vYdmKyEKN0mhsFLN5vhEZhd3Hn5AmFFpSpzpwIDAQABAkB"
                  + "OLvG5BrQG/AGZrw6d+jUg0"
                  + "ZBkWVJjqJZz3aUzcUsAfNTQYWKhUc3Wdb5At/qX5YumR0Z7v6OE7Si8XXTz1CbZAiEA0S0guE3U"
                  + "bHfmf2rVGtnwyMuRuO87XJ"
                  + "MkOcpiuJuIYC0CIQDEjGsrJ7xp+osaFwmuEfYvWEUDbt6UXdiur3m65kVzowIgX+EnllNWg0GSTeVS"
                  + "OTScwe1Npk9CDnUDrdmu1"
                  + "p+0KyECIQCc7ZhrfnB7FWWW0SoTZ5fuOUXw9caVI1DsOIKy7UQqjQIgV1BqZ7RTazekD+2U49qOB"
                  + "wRbutZTb2BmIUphCV6Q5NQ=";

  /**
   * 生成密钥对(公钥和私钥).
   */
  public static Map<String, Object> genKeyPair() throws Exception {
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
    keyPairGen.initialize(512);
    KeyPair keyPair = keyPairGen.generateKeyPair();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    Map<String, Object> keyMap = new HashMap<String, Object>(2);
    keyMap.put(PUBLIC_KEY, publicKey);
    keyMap.put(PRIVATE_KEY, privateKey);
    return keyMap;
  }

  /**
   * 用私钥对信息生成数字签名.
   *
   * @param data       已加密数据
   * @param privateKey 私钥(BASE64编码)
   */
  public static String sign(byte[] data, String privateKey) throws Exception {
    byte[] keyBytes = Base64Utils.decode(privateKey);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initSign(privateK);
    signature.update(data);
    return Base64Utils.encode(signature.sign());
  }

  /**
   * 校验数字签名.
   *
   * @param data      已加密数据
   * @param publicKey 公钥(BASE64编码)
   * @param sign      数字签名
   */
  public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
    byte[] keyBytes = Base64Utils.decode(publicKey);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    PublicKey publicK = keyFactory.generatePublic(keySpec);
    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
    signature.initVerify(publicK);
    signature.update(data);
    return signature.verify(Base64Utils.decode(sign));
  }

  /**
   * 私钥解密.
   *
   * @param encryptedData 已加密数据
   * @param privateKey    私钥(BASE64编码)
   */
  public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
          throws Exception {
    byte[] keyBytes = Base64Utils.decode(privateKey);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, privateK);
    int inputLen = encryptedData.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段解密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
        cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
      } else {
        cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_DECRYPT_BLOCK;
    }
    byte[] decryptedData = out.toByteArray();
    out.close();
    return decryptedData;
  }

  /**
   * 公钥解密.
   *
   * @param encryptedData 已加密数据
   * @param publicKey     公钥(BASE64编码)
   */
  public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
    byte[] keyBytes = Base64Utils.decode(publicKey);
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicK = keyFactory.generatePublic(x509KeySpec);
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, publicK);
    int inputLen = encryptedData.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段解密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
        cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
      } else {
        cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_DECRYPT_BLOCK;
    }
    byte[] decryptedData = out.toByteArray();
    out.close();
    return decryptedData;
  }

  /**
   * 公钥加密.
   *
   * @param data      源数据
   * @param publicKey 公钥(BASE64编码)
   */
  public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
    byte[] keyBytes = Base64Utils.decode(publicKey);
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key publicK = keyFactory.generatePublic(x509KeySpec);
    // 对数据加密
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, publicK);
    int inputLen = data.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段加密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
        cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
      } else {
        cache = cipher.doFinal(data, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_ENCRYPT_BLOCK;
    }
    byte[] encryptedData = out.toByteArray();
    out.close();
    return encryptedData;
  }

  /**
   * 私钥加密.
   *
   * @param data       源数据
   * @param privateKey 私钥(BASE64编码)
   */
  public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
    byte[] keyBytes = Base64Utils.decode(privateKey);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, privateK);
    int inputLen = data.length;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int offSet = 0;
    byte[] cache;
    int i = 0;
    // 对数据分段加密
    while (inputLen - offSet > 0) {
      if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
        cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
      } else {
        cache = cipher.doFinal(data, offSet, inputLen - offSet);
      }
      out.write(cache, 0, cache.length);
      i++;
      offSet = i * MAX_ENCRYPT_BLOCK;
    }
    byte[] encryptedData = out.toByteArray();
    out.close();
    return encryptedData;
  }

  /**
   * 获取私钥.
   *
   * @param keyMap 密钥对
   */
  public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
    Key key = (Key) keyMap.get(PRIVATE_KEY);
    return Base64Utils.encode(key.getEncoded());
  }

  /**
   * 获取公钥.
   *
   * @param keyMap 密钥对
   */
  public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
    Key key = (Key) keyMap.get(PUBLIC_KEY);
    return Base64Utils.encode(key.getEncoded());
  }

  /**
   * 使用RSA加密.
   */
  public static String encryptWithRsa(String original) {
    try {
      byte[] enCodeByte =
              RsaUtils.encryptByPublicKey(original.getBytes("utf-8"), RsaUtils.PUBLIC_RSA_KEY);
      return java.util.Base64.getEncoder().encodeToString(enCodeByte);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 使用RSA解密.
   */
  public static String decryptWithRsa(String encryptedString) {
    try {
      byte[] deCodeByte = RsaUtils.decryptByPrivateKey(
              java.util.Base64.getDecoder().decode(encryptedString), RsaUtils.PRIVATE_RSA_KEY);

      String deCodeSource = new String(deCodeByte, "utf-8");

      return deCodeSource;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

}
