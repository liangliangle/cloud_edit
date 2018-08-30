package com.doubi.edit.common.config;

// import org.apache.commons.codec.binary.Base64;

import org.jose4j.base64url.Base64;
import org.jose4j.jwk.RsaJsonWebKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class RsaKeyConfig {
  private final String privateKeyBase64 =
          "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCJK3NRq66QegcOVpw/el73NyrQ"
                  + "XChYUNZ5zu7lPiS72hKSo/LdGnmYNBBgBEMnCEBItGGwMNwK9s/eKOjygv7eQlsK4ikmY7j0+pok"
                  + "AHPi9GbefKqV5fm0LsxDwH5K4qdpKWSz1R4vBbDMOFVmEqSbjWjvFoYtN3LqgKM9R5hNYOw0XV3g"
                  + "+pGjWF/zzp3EJODfWc/2TLu4cTVD2MANlUzPyYfvr2IMxIkeSBiT2SnBFazIfpSkNHJUxh013Xcx"
                  + "Jx/SIjFecw72be+QfkKYsXjAnkyNb64F5k2aba0genQgobNALxTlTnOTHBWV4BgadhXI5O7LoHCX"
                  + "+SXN897d3MO9AgMBAAECggEBAIedV8QVMt9QZg18/7maqK1N85xQoOq/8Ww4dORczkCTV+12/jQv"
                  + "aqxaPR5Q2HuMotG+1R43EDYe0mDYnYa2kHj5rb1S4FA+I6zA3whdEjX+YH3zT0woNPo5kmYhuDsd"
                  + "X1qPPCq3t8HRpHwj7fqez/OimW5YHIwz1/+U8s8xK5jDiy//60naUmSKOSzF6ul+fFWellEweEU4"
                  + "fA8c3KgiPOWorjNZPjSQwXbbJuiljfiup5GISoMPZjon/WDhXCbD4+A8mClJVdyKm4Wo80MoR91I"
                  + "WhyzL+wQxZxj0wdxzRw9FzGoDVNoMJadlGKWaJpFXg+ih45E5YwE1PPAwlucI4ECgYEA+FeMYEzc"
                  + "DAh8+Ssv0/KKf3C+rDvLggTn76l8u779tnwNCDc2X2bSmAmj58a+CbeEa4CCfdsYVhmy2hMLXD62"
                  + "ISq1XNxmKl4kfwOGblHWQGOPye1xkEAXcXcDuxjvWVcoMVtAJ5ydnQeZR2mTJX+QnKCZDkK0PEPE"
                  + "GnM5ACDq/W0CgYEAjWZKWAaAcSzHvBDO7aoWczYIPgTuJ/h0RXRU39Xi1WSh5L9DP0PUmgKpdlDK"
                  + "V0VsHWG+5WCNJI+qOScDhSQc7FY09CfmGQBOdw2MCOtUZI2S6wF7Yjr5xNSyWnsE+XbZ06Mc55Cn"
                  + "6Ks8l6ok51mYOvD26RLfl/YR0ETj22z5fZECgYEAvvXDEYHIatBUGN0mh9mmudweIh5QnIfdOSKg"
                  + "sVVpjlrRQpICE1Xjddlj2aOvGZWuE2Pb8573la1pI9slDyeBsPtAPkmTndyKP8icJFR1ZUmplWIp"
                  + "wvwlirAzhDjmQ1KLq3g5WrxJTig7qvf5mAoBTw8G4jpUkUqijpwvJnQpffkCgYA4XJynp7fM5Oec"
                  + "nCqPa2vVcqUoa3iusAdQpAUsz6nCG4G4l1BryFLXLNZ1ldCBpudocszPy4OXHjjg3R83E+T/g4FK"
                  + "DRlXv9qG8/xslbkm95OnXwjaZ92lSo7pJ/4cnfZHuM3Ns8hqIs9dOPHK0divLduNnftILEp/dOZK"
                  + "UY7FMQKBgQDyDPbYDHGduyT6vJPaFcQGzQJOXnUPzFBH0rWOtV2HXjSlUfuT/D7j1HzPo8i7JUp8"
                  + "M0/gNzVigqk70bfJLLNDF+QtLv7P2XA1aqvOAGgyeSLElDPVybnC9FmvojireUsN/R9lC/9iONHo"
                  + "GgT5RZ90wy75MVDQl8FKEhmow/MBhA==";
  // private final String privateKeyBase64 =
  // this.readStringFromFile("com/goby/platform/jwt/private.key");
  private final String publicKeyBase64 =
          "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiStzUauukHoHDlacP3pe9zcq0FwoWFDW\n"
                  + "ec7u5T4ku9oSkqPy3Rp5mDQQYARDJwhASLRhsDDcCvbP3ijo8oL+3kJbCuIpJmO49PqaJABz4vRm"
                  + "3nyqleX5tC7MQ8B+SuKnaSlks9UeLwWwzDhVZhKkm41o7xaGLTdy6oCjPUeYTWDsNF1d4PqRo1hf"
                  + "886dxCTg31nP9ky7uHE1Q9jADZVMz8mH769iDMSJHkgYk9kpwRWsyH6UpDRyVMYdNd13MScf0iIx"
                  + "XnMO9m3vkH5CmLF4wJ5MjW+uBeZNmm2tIHp0IKGzQC8U5U5zkxwVleAYGnYVyOTuy6Bwl/klzfPe"
                  + "3dzDvQIDAQAB";
  // private final String publicKeyBase64 =
  // this.readStringFromFile("com/goby/platform/jwt/public.key");

  /**
   * 不知道干嘛用.
   */
  @Bean
  public RsaJsonWebKey rsaJsonWebKey() {
    RsaJsonWebKey rsaJsonWebKey = null;
    try {
      RSAPublicKey publicKey = getPublicKey(readByteArrayFromBase64String(publicKeyBase64));
      RSAPrivateKey privateKey = getPrivateKey(readByteArrayFromBase64String(privateKeyBase64));
      rsaJsonWebKey = new RsaJsonWebKey(publicKey);
      rsaJsonWebKey.setPrivateKey(privateKey);
      rsaJsonWebKey.setKeyId("bid-sec");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return rsaJsonWebKey;
  }

  private byte[] readByteArrayFromBase64String(String input) {
    return new Base64().decode(input);
  }

  private RSAPublicKey getPublicKey(byte[] keyBytes)
          throws NoSuchAlgorithmException, InvalidKeySpecException {
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return (RSAPublicKey) keyFactory.generatePublic(keySpec);
  }

  private RSAPrivateKey getPrivateKey(byte[] keyBytes)
          throws NoSuchAlgorithmException, InvalidKeySpecException {
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
    return (RSAPrivateKey) privateKey;
  }

  private String readStringFromFile(String fileName) {
    StringBuffer buffer = new StringBuffer();
    try {
      String encoding = "utf-8";
      InputStream inputStream = this.getClass().getResourceAsStream(fileName);
      // logger.info(file.getPath());
      InputStreamReader read = new InputStreamReader(inputStream, encoding);
      BufferedReader bufferedReader = new BufferedReader(read);
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        buffer.append(line);
      }
      read.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }
}