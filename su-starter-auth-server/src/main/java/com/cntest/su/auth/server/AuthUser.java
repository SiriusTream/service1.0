package com.cntest.su.auth.server;

import java.util.List;

/**
 * 认证用户。
 */
public interface AuthUser {
  /** 获取用户名 */
  String getUsername();

  /** 获取密码 */
  String getPassword();

  /** 是否可用 */
  Boolean isEnabled();

  /** 获取权限列表 */
  List<String> getPrivilegs();
}
