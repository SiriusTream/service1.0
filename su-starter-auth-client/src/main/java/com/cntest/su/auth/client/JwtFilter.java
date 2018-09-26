package com.cntest.su.auth.client;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.cntest.su.exception.SysException;

public class JwtFilter extends BasicHttpAuthenticationFilter {
  @Override
  protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
    String token = getAuthzHeader(request);
    return token != null;
  }

  @Override
  protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
    String token = getAuthzHeader(request);
    return new JwtToken(token);
  }

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
      Object mappedValue) {
    if (isLoginAttempt(request, response)) {
      try {
        executeLogin(request, response);
      } catch (Exception e) {
        redirectTo401(WebUtils.toHttp(response));
      }
    }
    return true;
  }

  private void redirectTo401(HttpServletResponse response) {
    try {
      response.sendRedirect("/401");
    } catch (IOException e) {
      throw new SysException("跳转401失败。");
    }
  }
}
