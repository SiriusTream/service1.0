package com.cntest.su.feign.handle;

import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 自定义WebMvcRegistrationsAdapter，过滤FeignClient的RequestMapping注解。
 */
public class FeignWebMvcRegistrations extends WebMvcRegistrationsAdapter {
  @Override
  public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
    return new FeignRequestMappingHandlerMapping();
  }
}
