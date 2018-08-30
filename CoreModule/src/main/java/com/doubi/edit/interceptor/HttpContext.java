package com.doubi.edit.interceptor;

/**
 * 保存请求的上下文.
 *
 * @author lianglianglee
 */
public class HttpContext {
  private String token;
  private String userId;
  private String userName;
  private String deviceType;

  private static final ThreadLocal<HttpContext> contextLocal = new ThreadLocal<HttpContext>() {
    @Override
    protected HttpContext initialValue() {
      return new HttpContext();
    }
  };

  private HttpContext() {
  }

  public static HttpContext getContext() {
    return contextLocal.get();
  }

  public static void removeContext() {
    contextLocal.remove();
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  /**
   * 当前使用者登录id.
   */
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
