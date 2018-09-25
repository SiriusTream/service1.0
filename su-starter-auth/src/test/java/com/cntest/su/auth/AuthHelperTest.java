package com.cntest.su.auth;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cntest.su.auth.config.AuthAutoConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {AuthAutoConfiguration.class})
public class AuthHelperTest {
  @Autowired
  private AuthHelper authHelper;

  @Test
  public void test() throws Exception {
    String token = authHelper.genToken("test", "password");
    String username = authHelper.getUsername(token);
    Assert.assertEquals("test", username);
    authHelper.verifyToken(token, "test", "password");
  }
}
