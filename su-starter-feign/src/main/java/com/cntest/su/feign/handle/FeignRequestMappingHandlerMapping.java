package com.cntest.su.feign.handle;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 自定义RequestMappingHandlerMapping，过滤FeignClient的RequestMapping注解。
 */
public class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
  @Override
  protected boolean isHandler(Class<?> beanType) {
    return super.isHandler(beanType)
        && (AnnotationUtils.findAnnotation(beanType, FeignClient.class) == null);
  }
}
