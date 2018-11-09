package com.cntest.su.auth.server;

/**
 * 认证服务。
 */
public interface AuthUserService {
  /**
   * 根据用户名获取认证用户。
   * 
   * @param username 用户名
   * @return 返回指定认证用户。
   */
  AuthUser getByUsername(String username);
}
