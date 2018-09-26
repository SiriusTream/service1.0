package com.cntest.su.auth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.cntest.su.auth.AuthService;
import com.cntest.su.auth.AuthUserInfo;
import com.cntest.su.auth.config.AuthProperties;

public class ClientAuthService implements AuthService {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private AuthProperties properties;

  @Override
  public AuthUserInfo getUserInfo(String token) {
    String url = "http://" + properties.getAuthServiceName() + "/user-info?token={token}";
    return restTemplate.getForObject(url, AuthUserInfo.class, token);
  }

  @Override
  public void checkToken(String token) {
    String url = "http://" + properties.getAuthServiceName() + "/check-token?token={token}";
    restTemplate.put(url, null, token);
  }
}
