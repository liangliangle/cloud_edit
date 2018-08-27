package com.doubi.edit.common.exception;

/**
 * 禁止访问的异常.
 * @author 李亮亮
 */
public class ForbiddenException extends BaseException {

  private static final long serialVersionUID = 1L;

  public ForbiddenException() {
  }

  public ForbiddenException(String message) {
    super(message);
  }

  public ForbiddenException(Throwable cause) {
    super(cause);
  }

  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

  public ForbiddenException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
