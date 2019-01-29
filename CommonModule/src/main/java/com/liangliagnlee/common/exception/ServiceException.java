package com.liangliagnlee.common.exception;

public class ServiceException extends BaseException {


  private static final long serialVersionUID = -1947137420231183660L;

  public ServiceException() {
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(String message, Throwable cause, boolean enableSuppression,
                          boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
