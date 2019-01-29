package org.lianglianglee.oss.exception;

/**
 * OSS异常.
 *
 * @author liangliang
 * @date 2018年4月19日
 */
public class OssException extends RuntimeException {

  private static final long serialVersionUID = 2451378532336067318L;

  public OssException() {
    super();
  }

  public OssException(String message) {
    super(message);
  }

  public OssException(String message, Throwable cause) {
    super(message, cause);
  }

  public OssException(Throwable cause) {
    super(cause);
  }


}
