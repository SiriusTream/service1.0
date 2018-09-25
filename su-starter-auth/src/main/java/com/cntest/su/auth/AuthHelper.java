package com.cntest.su.auth;

import java.io.UnsupportedEncodingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cntest.su.auth.config.AuthProperties;
import com.cntest.su.exception.SysException;

public class AuthHelper {
  @Autowired
  private AuthProperties properties;

  /**
   * 生成Token。
   * 
   * @param username 用户名
   * @param password 密码
   * @return 返回Token。
   */
  public String genToken(String username, String password) {
    try {
      DateTime expiredTime = DateTime.now().plusMinutes(properties.getTimeout());
      return JWT.create().withClaim("username", username).withExpiresAt(expiredTime.toDate())
          .sign(Algorithm.HMAC256(password));
    } catch (UnsupportedEncodingException e) {
      throw new SysException("生成Token时发生异常。", e);
    }
  }

  /**
   * 验证Token。
   * 
   * @param token Token
   * @param username 用户名
   * @param password 密码
   */
  public void verifyToken(String token, String username, String password) {
    try {
      JWTVerifier verifier =
          JWT.require(Algorithm.HMAC256(password)).withClaim("username", username).build();
      verifier.verify(token);
    } catch (Exception e) {
      throw new AuthenticationException("验证Token失败。", e);
    }
  }

  /**
   * 从Token中获取用户名。
   * 
   * @return 返回Token中包含的用户名。
   */
  public String getUsername(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("username").asString();
    } catch (JWTDecodeException e) {
      throw new AuthenticationException("从Token中获取用户名失败。", e);
    }
  }

  /**
   * 加密密码。
   * 
   * @param password 待加密的密码
   * @return 返回加密后的密码。
   */
  public String encodePassword(String password) {
    return new SimpleHash(properties.getAlgorithm(), password, getSaltByteSource()).toBase64();
  }

  /**
   * 检查密码是否正确。
   * 
   * @param password 原密码
   * @param hashedPassword 加密后的密码
   * @return 如果密码正确返回true，否则返回false。
   */
  public Boolean verifyPassword(String password, String hashedPassword) {
    return encodePassword(password).equals(hashedPassword);
  }

  /**
   * 获取盐值字节源。
   * 
   * @return 返回盐值字节源。
   */
  public ByteSource getSaltByteSource() {
    return ByteSource.Util.bytes(properties.getSalt());
  }
}
