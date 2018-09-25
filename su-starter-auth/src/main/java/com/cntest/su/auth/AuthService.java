package com.cntest.su.auth;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthService {
  AuthUserInfo getUserInfo(@NotBlank(message = "令牌不能为空") String token);

  void checkToken(@NotBlank(message = "令牌不能为空") String token);
}
