package com.cntest.su.auth;

import java.util.ArrayList;
import java.util.List;

public class AuthUserInfo {
  private String username;
  private List<String> privilegs = new ArrayList<>();

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getPrivilegs() {
    return privilegs;
  }

  public void setPrivilegs(List<String> privilegs) {
    this.privilegs = privilegs;
  }
}
