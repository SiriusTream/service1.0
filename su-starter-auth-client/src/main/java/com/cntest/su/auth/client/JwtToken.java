package com.cntest.su.auth.client;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
  private static final long serialVersionUID = -1927918549066601185L;
  private String token;

  public JwtToken(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return getPrincipal();
  }
}
