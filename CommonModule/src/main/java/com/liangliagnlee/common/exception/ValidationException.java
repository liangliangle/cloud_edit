package com.liangliagnlee.common.exception;

/**
 * @author 李亮亮
 */
public class ValidationException extends BaseException {

  private static final long serialVersionUID = -997461078221661733L;

  public ValidationException() {
  }

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(Throwable cause) {
    super(cause);
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidationException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
