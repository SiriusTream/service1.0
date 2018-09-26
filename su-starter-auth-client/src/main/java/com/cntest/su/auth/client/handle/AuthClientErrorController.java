package com.cntest.su.auth.client.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cntest.su.message.MessageSource;
import com.cntest.su.web.handler.ErrorResponse;

@RestController
public class AuthClientErrorController {
  @Autowired
  private MessageSource messageSource;

  @RequestMapping("/401")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse handle401() {
    return new ErrorResponse("E980", messageSource.get("E980"));
  }
}
