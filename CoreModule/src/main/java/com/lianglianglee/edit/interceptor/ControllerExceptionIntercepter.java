package com.lianglianglee.edit.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.liangliagnlee.common.exception.AuthorizationException;
import com.liangliagnlee.common.exception.DaoException;
import com.liangliagnlee.common.exception.ForbiddenException;
import com.liangliagnlee.common.exception.NotFoundException;
import com.liangliagnlee.common.exception.ServiceException;
import com.liangliagnlee.common.exception.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * 全局处理controller中的异常 - 所有的ValidationException的标识为200, 表示客户端已经联通，但是服务端有异常.
 * 所有未捕获的RuntimeException的标识为500, 表示服务端有异常。
 *
 * @author lianglianglee
 */
@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionIntercepter {
  private final Logger logger = LoggerFactory.getLogger(ControllerExceptionIntercepter.class);


  @ExceptionHandler(value = {NotFoundException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Object notFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
    return evaluateExceptionObject(req, e,
      "---controllerExceptionHandler ---Host {} invokes url {} ERROR: {}");
  }

  @ExceptionHandler(value = {ValidationException.class, ServiceException.class, DaoException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object controllerExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
    return evaluateExceptionObject(req, e,
      "---controllerExceptionHandler ---Host {} invokes url {} ERROR: {}");
  }


  @ExceptionHandler(value = {ForbiddenException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Object forbiddenExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
    return evaluateExceptionObject(req, e,
      "---controllerExceptionHandler ---Host {} invokes url {} ERROR: {}");
  }


  private Object evaluateExceptionObject(HttpServletRequest req, Exception e, String s) {
    logger.error(s, req.getRemoteHost(), req.getRequestURL(), e.getMessage());
    Map<String, String> map = new HashMap<String, String>();
    map.put("message", e.getMessage());
    e.printStackTrace();
    return map;
  }


  @ExceptionHandler(value = {AuthorizationException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Object unauthorizedException(HttpServletRequest request, Exception e) {
    return evaluateExceptionObject(request, e,
      "---Serivice Handler ---Host {} invokes url {} ERROR: {}");
  }

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Object runtimeExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
    return evaluateExceptionObject(req, e,
      "---Serivice Handler ---Host {} invokes url {} ERROR: {}");
  }
}
