package com.cntest.su.auth.server.api;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cntest.su.auth.AuthHelper;
import com.cntest.su.auth.AuthService;
import com.cntest.su.auth.AuthToken;
import com.cntest.su.auth.AuthUserInfo;
import com.cntest.su.auth.server.AuthUser;
import com.cntest.su.auth.server.AuthUserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthApi implements AuthService {
  @Autowired
  private AuthUserService authUserService;
  @Autowired
  private AuthHelper authHelper;

  @ApiOperation(value = "获取令牌")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
      @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
  @RequestMapping(path = "/access-token", method = {RequestMethod.GET, RequestMethod.POST})
  public AuthToken getToken(@NotBlank(message = "用户名不能为空") @RequestParam String username,
      @NotBlank(message = "密码不能为空") @RequestParam String password) {
    AuthUser user = authUserService.getByUsername(username);

    if (user == null) {
      throw new UnknownAccountException();
    }
    if (!user.isEnabled()) {
      throw new DisabledAccountException();
    }
    if (!authHelper.verifyPassword(password, user.getPassword())) {
      throw new IncorrectCredentialsException();
    }

    AuthToken token = new AuthToken();
    token.setUsername(username);
    token.setToken(authHelper.genToken(user.getUsername(), user.getPassword()));
    return token;
  }

  @Override
  @ApiOperation(value = "获取用户信息")
  @ApiImplicitParam(name = "token", value = "令牌", required = true, dataType = "String")
  @RequestMapping(path = "/user-info", method = RequestMethod.GET)
  public AuthUserInfo getUserInfo(@RequestParam String token) {
    AuthUser user = getUser(token);

    AuthUserInfo userInfo = new AuthUserInfo();
    userInfo.setUsername(user.getUsername());
    userInfo.setPrivilegs(user.getPrivilegs());
    return userInfo;
  }

  @Override
  @ApiOperation(value = "验证令牌")
  @ApiImplicitParam(name = "token", value = "令牌", required = true, dataType = "String")
  @RequestMapping(path = "/check-token", method = RequestMethod.PUT)
  public void checkToken(@RequestParam String token) {
    AuthUser user = getUser(token);
    authHelper.verifyToken(token, user.getUsername(), user.getPassword());
  }

  private AuthUser getUser(String token) {
    String username = authHelper.getUsername(token);
    AuthUser user = authUserService.getByUsername(username);
    if (user == null) {
      throw new UnknownAccountException();
    }
    if (!user.isEnabled()) {
      throw new DisabledAccountException();
    }
    return user;
  }
}
