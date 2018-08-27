package com.doubi.edit.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 将从传来的值，存储在线程上下文中。供service, controller调用.
 */
@Service
public class ContextInitInterceptor implements HandlerInterceptor {

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    HttpContext.removeContext();
  }

  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o) throws Exception {
    // roles 是中文, gateway已做encode, 所以需要做decode
    String originalRoles =
        httpServletRequest.getHeader("roles") == null ? "" : httpServletRequest.getHeader("roles");
    String roles = URLDecoder.decode(originalRoles, "utf-8");
    HttpContext.getContext().setToken(httpServletRequest.getHeader("Authorization"));
    HttpContext.getContext().setUserId(httpServletRequest.getHeader("userId"));
    HttpContext.getContext()
        .setDeviceType(StringUtils.isEmpty(httpServletRequest.getHeader("deviceType")) ? "WEB"
            : httpServletRequest.getHeader("deviceType"));

    final String encodedUserName = httpServletRequest.getHeader("userName") == null ? ""
        : httpServletRequest.getHeader("userName");
    HttpContext.getContext().setUserName(URLDecoder.decode(encodedUserName, "utf-8"));
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
      throws Exception {

  }
}
