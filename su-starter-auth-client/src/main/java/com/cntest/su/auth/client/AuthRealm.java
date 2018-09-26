package com.cntest.su.auth.client;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cntest.su.auth.AuthService;
import com.cntest.su.auth.AuthUserInfo;

/**
 * 认证组件。
 */
public class AuthRealm extends AuthorizingRealm {
  @Autowired
  private AuthService authService;

  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JwtToken;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
    String jwtToken = token.getCredentials().toString();
    authService.checkToken(jwtToken);
    return new SimpleAuthenticationInfo(jwtToken, jwtToken, getName());
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String jwtToken = getAvailablePrincipal(principals).toString();
    AuthUserInfo userinfo = authService.getUserInfo(jwtToken);

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addStringPermissions(userinfo.getPrivilegs());
    return info;
  }
}
