package com.cntest.su.auth.config;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 认证配置属性。
 */
@ConfigurationProperties(prefix = "su.auth")
public class AuthProperties {
  /** 加密算法 */
  private String algorithm = Sha256Hash.ALGORITHM_NAME;
  /** 加密盐值 */
  private String salt = Sha256Hash.ALGORITHM_NAME;
  /** Token过期时间 */
  private Integer timeout = 120;
  /** 认证服务名称 */
  private String authServiceName = "auth-server";


  public String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public Integer getTimeout() {
    return timeout;
  }

  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }

  public String getAuthServiceName() {
    return authServiceName;
  }

  public void setAuthServiceName(String authServiceName) {
    this.authServiceName = authServiceName;
  }
}
