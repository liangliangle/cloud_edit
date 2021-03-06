package com.liangliagnlee.common.exception;

/**
 * @author 李亮亮
 */
public class NotFoundException extends BaseException {

  private static final long serialVersionUID = -2917296303778681156L;

  public NotFoundException() {
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
